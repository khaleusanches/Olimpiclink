using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Data;
using olimpiclink.database.Models.Users;

namespace olimpiclink.database.Controllers
{
    [ApiController]
    [Route("api/v1/cities")]  //define a rota da url
    public class CityController : Controller
    {
        ConnectionContext context = new ConnectionContext();

        [HttpGet]
        public async Task<IActionResult> cityGet(CancellationToken ct)
        {
            
            var city = await context.cities.ToListAsync(ct);
            return Ok(city);
        }

        [HttpGet("id/{id_city}")]
        public async Task<IActionResult> cityGetID(long? id_city, CancellationToken ct)
        {
            var city_specific = await context.cities.Where(city => city.id_city == id_city).FirstOrDefaultAsync(ct);
            if (city_specific == null)
            {
                return Ok("Essa cidade não existe");
            }
            return Ok(city_specific);
        }

        [HttpGet("name/{name_city}")]
        public async Task<IActionResult> cityGetName(string name_city, CancellationToken ct)
        {
            var city_specific = await context.cities.Where(city => city.name_city == name_city).FirstOrDefaultAsync(ct);
            if (city_specific == null)
            {
                return Ok("Essa cidade não existe");
            }
            return Ok(city_specific);
        }

        [HttpPost("usercity")]
        public async Task<IActionResult> postUserCity(string login_user, int id_city, CancellationToken ct)
        {
            var user = context.users.FirstOrDefaultAsync(user => user.login_user == login_user);
            var new_user_city = new UserCity(user.Result.id_user, id_city);
            await context.user_city.AddAsync(new_user_city);
            await context.SaveChangesAsync();
            return Ok();
        }
    }
}
