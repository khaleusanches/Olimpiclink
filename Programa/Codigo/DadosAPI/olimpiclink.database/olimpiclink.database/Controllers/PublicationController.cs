using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Data;
using olimpiclink.database.Models;
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
                    url_profile_picture_user = (await context.users.SingleOrDefaultAsync(user => user.id_user == publications[i].user_id)).url_profile_picture_user,
                    text_publication = publications[i].text_publication,
                    url_image_one_publication = publications[i].url_image_one_publication,
                    url_image_two_publication = publications[i].url_image_two_publication,
                    url_image_three_publication = publications[i].url_image_three_publication,
                    url_image_four_publication = publications[i].url_image_four_publication,
                    date_publication = publications[i].date_publication,
                    comunity_id = publications[i].comunity_id,
                    place_id = publications[i].place_id,
                    event_id = publications[i].event_id
                });
            }
            return Ok(lista);
        }
        [HttpGet("imagens/{id}/{img}")]
        public async Task<IActionResult> getImagesPublication(int id, int img)
        {
            byte[] publicationImage = null;
            switch (img){
                case 1:
                    publicationImage = (await context.publications.FindAsync(id)).image_one_publication;
                    break;
                case 2:
                    publicationImage = (await context.publications.FindAsync(id)).image_two_publication;
                    break;
                case 3:
                    publicationImage = (await context.publications.FindAsync(id)).image_three_publication;
                    break;
                case 4:
                    publicationImage = (await context.publications.FindAsync(id)).image_four_publication;
                    break;
            }
            if(publicationImage == null)
            {
                return BadRequest();
            }
            return File(publicationImage, "image/jpeg");
        }

        [HttpPost]
        public async Task<IActionResult> postImagePublication(
            [FromForm] int id, IFormFile image_one_publication = null, 
            IFormFile image_two_publication = null, 
            IFormFile image_three_publication = null, 
            IFormFile image_four_publication = null
        )
        {
            
            var publication = await context.publications.FindAsync(id);

            if (image_one_publication != null)
            {
                var memoryStream = new MemoryStream();
                image_one_publication.CopyTo(memoryStream);
                publication.image_one_publication = memoryStream.ToArray();
                publication.url_image_one_publication = "http://192.168.0.158:5000/api/publication/imagens/" + id + "/1";
                memoryStream.Close();
            }
            if(image_two_publication != null)
            {
                var memoryStream = new MemoryStream();
                image_two_publication.CopyTo(memoryStream);
                publication.image_two_publication = memoryStream.ToArray();
                publication.url_image_two_publication = "http://192.168.0.158:5000/api/publication/imagens/" + id + "/2";
                memoryStream.Close();
            }
            if(image_three_publication != null)
            {
                var memoryStream = new MemoryStream();
                image_three_publication.CopyTo(memoryStream);
                publication.image_three_publication = memoryStream.ToArray();
                publication.url_image_three_publication = "http://192.168.0.158:5000/api/publication/imagens/" + id + "/3";
                memoryStream.Close();
            }
            if (image_four_publication != null)
            {
                var memoryStream = new MemoryStream();
                image_four_publication.CopyTo(memoryStream);
                publication.image_four_publication = memoryStream.ToArray();
                publication.url_image_four_publication = "http://192.168.0.158:5000/api/publication/imagens/" + id + "/4";
                memoryStream.Close();
            }
            await context.SaveChangesAsync();
            return Ok();
        }

        [HttpPost("pointo")]
        public async Task<IActionResult> pint(AddPublicationModel new_add_publication)
        {
            PublicationModel new_publication = new 
                PublicationModel(
                new_add_publication.user_id,
                new_add_publication.text_publication,
                new_add_publication.image_one_publication,
                new_add_publication.image_two_publication,
                new_add_publication.image_three_publication,
                new_add_publication.image_four_publication,
                new_add_publication.comunity_id,
                new_add_publication.place_id,
                new_add_publication.event_id
                );
            if (new_publication.image_one_publication != null)
            {
                new_publication.url_image_one_publication = "http://192.168.0.158:5000/api/publication/imagens/" + new_publication.id_publication + "/1";
            }
            if (new_publication.image_two_publication != null)
            {
                new_publication.url_image_one_publication = "http://192.168.0.158:5000/api/publication/imagens/" + new_publication.id_publication + "/2";
            }
            if (new_publication.image_three_publication != null)
            {
                new_publication.url_image_one_publication = "http://192.168.0.158:5000/api/publication/imagens/" + new_publication.id_publication + "/3";
            }
            if (new_publication.image_four_publication != null)
            {
                new_publication.url_image_one_publication = "http://192.168.0.158:5000/api/publication/imagens/" + new_publication.id_publication + "/4";
            }
            var teste = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            var test2 = DateTime.ParseExact(teste, "yyyy-MM-dd HH:mm:ss", null);
            new_publication.date_publication = test2;
            await context.publications.AddAsync(new_publication);
            await context.SaveChangesAsync();
            return Ok();
        }
    }
}
