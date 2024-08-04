using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Data;
using olimpiclink.database.Models.Users;

namespace olimpiclink.database.Controllers
{
    [ApiController]
    [Route("api/v1/user")]  //define a rota da url
    public class UserController : Controller
    {
        [HttpPost]
        public async Task<IActionResult> Add(AddUserModel request, CancellationToken ct)
        {
            ConnectionContext context = new ConnectionContext();
            var new_user = new UserModel(request.name_user, request.email_user, request.password_user, request.login_user);

            var check_email = await context.Users.AnyAsync(user => user.email_user == request.email_user);
            var check_login = await context.Users.AnyAsync(user => user.login_user == request.login_user);

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
    }
}
