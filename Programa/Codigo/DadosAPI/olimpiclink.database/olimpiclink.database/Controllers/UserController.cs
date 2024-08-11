using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Data;
using olimpiclink.database.Models.Users;

namespace olimpiclink.database.Controllers
{
    [ApiController]
    [Route("api/v1/users")]  //define a rota da url
    public class UserController : Controller
    {
        [HttpGet("add/name={name_user}login={login_user}email={email_user}pass={password_user}")]
        public async Task<IActionResult> Add(string name_user, string email_user, string password_user, string login_user, CancellationToken ct)
        {
            ConnectionContext context = new ConnectionContext();
            var new_user = new UserModel(name_user, email_user, password_user, login_user);

            var check_email = await context.Users.AnyAsync(user => user.email_user == email_user && user.activated_user == true);
            var check_login = await context.Users.AnyAsync(user => user.login_user == login_user && user.activated_user == true);

            if (check_email)
            {
                return Ok("Este email já esta sendo usado");
            }
            else if (check_login)
            {
                return Ok("Esse login já esta sendo usado");
            }

            await context.Users.AddAsync(new_user); 
            await context.SaveChangesAsync(ct);
            return Ok();
        }

        [HttpGet]
        public async Task<IActionResult> Get(CancellationToken ct)
        {
            ConnectionContext context = new ConnectionContext();
            var get_users = await context.Users.ToListAsync(ct);
            return Ok(get_users);
        }

        [HttpGet("{id_user}")]
        public async Task<IActionResult> GetID(long? id_user, CancellationToken ct)
        {
            ConnectionContext context = new ConnectionContext();
            var get_users = await context.Users.Where(user => user.id_user == id_user).ToListAsync(ct);
            return Ok(get_users);
        }

        [HttpGet("alter/{id_user}/name={name_user}login={login_user}email={email_user}pass={password_user}")]
        public async Task<IActionResult> Put(int id_user, string name_user, string email_user, string password_user, string login_user, CancellationToken ct)
        {
            ConnectionContext context = new ConnectionContext();
            var user = await context.Users.SingleOrDefaultAsync(user => user.id_user == id_user, ct);
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
        public async Task<IActionResult> Teste(int id_user, CancellationToken ct)
        {
            ConnectionContext context = new ConnectionContext();
            var user = await context.Users.FindAsync(id_user, ct);
            if(user == null)
            {
                return Ok("Esse usuário não existe");
            }
            user.activated_user = false;
            await context.SaveChangesAsync(ct);
            return Ok();
        }
    }
}
