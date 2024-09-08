using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Data;
using olimpiclink.database.Models.Users;

namespace olimpiclink.database.Controllers
{
    [ApiController]
    [Route("api/publication")]
    public class PublicationController : Controller
    {
        ConnectionContext context = new ConnectionContext();
        [HttpGet]
        public async Task<IActionResult> getPublication()
        {
            var publications = await context.publications.ToListAsync();
            var lista = new List<object>();
            for (int i = 0; i < publications.Count(); i++)
            {
                lista.Add(new {
                    id_publication = publications[i].id_publication,
                    user_id = publications[i].user_id,
                    login_user = (await context.users.SingleOrDefaultAsync(user => user.id_user == publications[i].user_id)).login_user,
                    profile_picture_user = (await context.users.SingleOrDefaultAsync(user => user.id_user == publications[i].user_id)).profile_picture_user,
                    text_publication = publications[i].text_publication,
                    images = new
                    {
                        image_one_publication = publications[i].image_one_publication,
                        image_two_publication = publications[i].image_two_publication,
                        image_three_publication = publications[i].image_three_publication,
                        image_four_publication = publications[i].image_four_publication,
                    },
                    date_publication = publications[i].date_publication,
                    comunity_id = publications[i].comunity_id,
                    place_id = publications[i].place_id,
                    event_id = publications[i].event_id
                });
            }
            return Ok(lista);
        }
    }
}
