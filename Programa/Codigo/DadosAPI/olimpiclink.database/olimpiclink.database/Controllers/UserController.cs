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
        [HttpPost]
        public async Task<IActionResult> Add(AddUserModel request, CancellationToken ct)
        {
            ConnectionContext context = new ConnectionContext();
            var new_user = new UserModel(request.name_user, request.email_user, request.password_user, request.login_user);

            var check_email = await context.Users.AnyAsync(user => user.email_user == request.email_user && user.activated_user == true);
            var check_login = await context.Users.AnyAsync(user => user.login_user == request.login_user && user.activated_user == true);

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

        [HttpPut]
        public async Task<IActionResult> Put(int id, AddUserModel request, CancellationToken ct)
        {
            ConnectionContext context = new ConnectionContext();
            var user = await context.Users.SingleOrDefaultAsync(user => user.id_user == id, ct);
            if(user == null)
            {
                return Ok("Esse usuário não existe");
            }
            user.name_user = request.name_user;
            user.email_user = request.email_user;
            user.password_user = request.password_user;
            user.login_user = request.login_user;
            await context.SaveChangesAsync(ct);
            return Ok();
        }

        [HttpDelete]
        public async Task<IActionResult> Teste(int id, CancellationToken ct)
        {
            ConnectionContext context = new ConnectionContext();
            var user = await context.Users.FindAsync(id, ct);
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
