using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Data;
using olimpiclink.database.Models;
using olimpiclink.database.Models.Users;

namespace olimpiclink.database.Controllers
{
    [ApiController]
    [Route("api/users")]  //define a rota da url
    public class UserController : Controller
    {
        ConnectionContext context = new ConnectionContext();
        [HttpPost]
        public async Task<IActionResult> userCreate(AddUserModel user, CancellationToken ct)
        {
            var new_user = new UserModel(user.name_user, user.email_user, user.password_user, user.login_user);
            var check_email = await context.users.AnyAsync(usere => usere.email_user == user.email_user && usere.activated_user == true);
            var check_login = await context.users.AnyAsync(usere => usere.login_user == user.login_user && usere.activated_user == true);

            if (check_email)
            {
                return Ok("Este email já esta sendo usado");
            }
            else if (check_login)
            {
                return Ok("Esse login já esta sendo usado");
            }

            await context.users.AddAsync(new_user); 
            await context.SaveChangesAsync(ct);

            var city = await context.cities.FirstOrDefaultAsync(city => city.name_city == user.city_name);
            await new CityController().postUserCity(user.login_user, city.id_city, ct);

            foreach(var category in user.categories)
            {
                var categori = await context.categories.FirstOrDefaultAsync(categorie => categorie.name_category == category);
                await new CategoryController().postUserCategory(user.login_user, categori.id_category);
            }
            return Ok();
        }

        [HttpGet]
        public async Task<IActionResult> userGet(CancellationToken ct)
        {
            var get_users = await context.users.ToListAsync(ct);
            return Ok(get_users);
        }
        [HttpGet("imagem")]
        public async Task<IActionResult> userGetImage(int id, CancellationToken ct)
        {
            var get_users = await context.users.Where(user => user.id_user == id).ToListAsync(ct);
            return File(get_users[0].profile_picture_user, "image/jpeg");
        }

        [HttpGet("{login_user}")]
        public async Task<IActionResult> userGetLogin(string login_user, CancellationToken ct)
        {
            var get_users = await context.users.SingleOrDefaultAsync(user => user.login_user == login_user);
            return Ok(get_users);
        }

        [HttpGet("ide/{id}")]
        public async Task<IActionResult> userGetID(int id, CancellationToken ct)
        {
            var get_users = await context.users.SingleOrDefaultAsync(user => user.id_user == id);
            return Ok(get_users);
        }

        [HttpGet("alter/{id_user}/name={name_user}login={login_user}email={email_user}pass={password_user}")]
        public async Task<IActionResult> userChanged(int id_user, string name_user, string email_user, string password_user, string login_user, CancellationToken ct)
        {
            var user = await context.users.SingleOrDefaultAsync(user => user.id_user == id_user, ct);
            if(user == null)
            {
                return Ok("Esse usuário não existe");
            }
            user.name_user = name_user;
            user.email_user = email_user;
            user.password_user = password_user;
            user.login_user = login_user;
            await context.SaveChangesAsync(ct);
            return Ok();
        }

        [HttpGet("delete/{id_user}")]
        public async Task<IActionResult> userDelete(int id_user, CancellationToken ct)
        {
            var user = await context.users.FindAsync(id_user, ct);
            if(user == null)
            {
                return Ok("Esse usuário não existe");
            }
            user.activated_user = false;
            await context.SaveChangesAsync(ct);
            return Ok();
        }

        [HttpGet("login/{login_user}&{password_user}")]
        public async Task<IActionResult> userLogin(string login_user, string password_user, CancellationToken ct)
        {
            var user = await context.users.SingleOrDefaultAsync(user => user.login_user == login_user && user.password_user == password_user, ct);
            if (user == null)
            {
                return BadRequest("Usuário ou senha incorretos");
            }
            return Ok(user);
        }
        [HttpGet("check/{login_user}&{email_user}")]
        public async Task<IActionResult> userCheckExists(string login_user, string email_user)
        {
            var check_email = await context.users.AnyAsync(user => user.email_user == email_user && user.activated_user == true);
            var check_user = await context.users.AnyAsync(user => user.login_user == login_user && user.activated_user == true);
            var respostaErro = new { message = "Pode continuar" };
            if (check_email)
            {
                if(check_user)
                {
                    respostaErro = new { message = "username e email já cadastrados" };
                    return Ok(respostaErro);
                }
                else
                {
                    respostaErro = new { message = "email já cadastrados" };
                    return Ok(respostaErro);
                }
            }
            else if (check_user)
            {
                respostaErro = new { message = "login_user já cadastrado" };
                return Ok(respostaErro);
            }
            return Ok(respostaErro);
        }
        [HttpGet("userCategories/{id}")]
        public async Task<IActionResult> getUserCategories(int id)
        {
            var urlCategorys = await(
                from users in context.users
                join user_category in context.user_category
                on users.id_user equals user_category.user_id
                join category in context.categories
                on user_category.category_id equals category.id_category
                where users.id_user == id
                select category.url_icon_category
                ).ToListAsync();
            return Ok(urlCategorys);
        }
    }
}
