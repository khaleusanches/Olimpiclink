using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Data;

namespace olimpiclink.database.Controllers
{
    [ApiController]
    [Route("api/v1/cities")]  //define a rota da url
    public class CityController : Controller
    {

        [HttpGet]
        public async Task<IActionResult> Get(int? id, CancellationToken ct)
        {
            ConnectionContext context = new ConnectionContext();
            if (id == null)
            {
                var city = await context.cities.ToListAsync(ct);
                return Ok(city);
            }
            var city_specific = await context.cities.Where(city => city.id_city == id).FirstOrDefaultAsync(ct);
            if (city_specific == null)
            {
                return Ok("Essa cidade não existe");
            }
            return Ok(city_specific);
        }
    }
}
