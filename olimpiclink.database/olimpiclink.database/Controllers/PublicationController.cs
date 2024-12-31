﻿using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Data;
using olimpiclink.database.Models;
using olimpiclink.database.Models.Publications;
using olimpiclink.database.Models.Users;

namespace olimpiclink.database.Controllers
{
    [ApiController]
    [Route("api/publication")]
    public class PublicationController : Controller
    {
        ConnectionContext context = new ConnectionContext();

        [HttpPost]
        public async Task<IActionResult> post(AddPublicationModel new_add_publication)
        {
            byte[]? img_one = null;
            byte[]? img_two = null;
            byte[]? img_three = null;
            byte[]? img_four = null;
            if (new_add_publication.image_one_publication != null)
            {
                img_one = Convert.FromBase64String(new_add_publication.image_one_publication.Trim().Replace(" ", "").Replace("\n", ""));
            }
            if (new_add_publication.image_two_publication != null)
            {
                img_two = Convert.FromBase64String(new_add_publication.image_two_publication.Trim().Replace(" ", "").Replace("\n", ""));
            }
            if (new_add_publication.image_three_publication != null)
            {
                img_three = Convert.FromBase64String(new_add_publication.image_three_publication.Trim().Replace(" ", "").Replace("\n", ""));
            }
            if (new_add_publication.image_four_publication != null)
            {
                img_four = Convert.FromBase64String(new_add_publication.image_four_publication.Trim().Replace(" ", "").Replace("\n", ""));
            }
            PublicationModel new_publication = new
                PublicationModel(
                new_add_publication.user_id,
                new_add_publication.text_publication,
                true,
                img_one,
                img_two, img_three, img_four,
                new_add_publication.comunity_id,
                new_add_publication.place_id,
                new_add_publication.event_id
                );
            var id = context.publications.ToListAsync().Result.Count() + 1;
            if (new_publication.image_one_publication != null)
            {
                new_publication.url_image_one_publication = "http://192.168.0.158:5000/api/publication/imagens/" + id + "/1";
            }
            if (new_publication.image_two_publication != null)
            {
                new_publication.url_image_two_publication = "http://192.168.0.158:5000/api/publication/imagens/" + id + "/2";
            }
            if (new_publication.image_three_publication != null)
            {
                new_publication.url_image_three_publication = "http://192.168.0.158:5000/api/publication/imagens/" + id + "/3";
            }
            if (new_publication.image_four_publication != null)
            {
                new_publication.url_image_four_publication = "http://192.168.0.158:5000/api/publication/imagens/" + id + "/4";
            }
            var teste = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            var test2 = DateTime.ParseExact(teste, "yyyy-MM-dd HH:mm:ss", null);
            new_publication.date_publication = test2;
            await context.publications.AddAsync(new_publication);
            await context.SaveChangesAsync();
            return Ok();
        }

        [HttpGet("feed/{id}")]
        public async Task<IActionResult> getPublication(int id)
        {
            var getPublication = await (from publication in context.publications
                                join uc1 in context.user_category on publication.user_id equals uc1.user_id
                                join uc2 in context.user_category on uc1.category_id equals uc2.category_id
                                where uc2.user_id == id
                                select new
                                {
                                    publication.id_publication,
                                    publication.user_id,
                                    publication.text_publication,
                                    publication.url_image_one_publication,
                                    publication.url_image_two_publication,
                                    publication.url_image_three_publication,
                                    publication.url_image_four_publication,
                                    publication.date_publication,
                                    publication.comunity_id,
                                    publication.place_id,
                                    publication.event_id
                                }).ToListAsync();
            return Ok(getPublication);
        }
        [HttpGet("user/{user_id}")]
        public async Task<IActionResult> getUserPublications(int user_id)
        {
            var teste = new PlaceController();
            var publications = await context.publications.Where(publication => publication.user_id == user_id && publication.activated_publication == true).OrderByDescending(user => user.date_publication).ToListAsync();
            var lista = new List<GetPublicationModel>();
            foreach (var publication in publications)
            {
                var getPublication = new GetPublicationModel
                    (
                        publication.id_publication,
                        publication.user_id,
                        publication.text_publication,
                        publication.url_image_one_publication,
                        publication.url_image_two_publication,
                        publication.url_image_three_publication,
                        publication.url_image_four_publication,
                        publication.date_publication,
                        publication.comunity_id,
                        publication.place_id,
                        publication.event_id
                    );
                lista.Add(getPublication);
            }
            return Ok(lista);
        }

        [HttpGet("user/{user_id}/galery")]
        public async Task<IActionResult> getUserPublicationsGalery(int user_id)
        {
            var teste = new PlaceController();
            var publications = await context.publications.Where(publication => publication.user_id == user_id && publication.activated_publication == true && publication.image_one_publication != null).OrderByDescending(user => user.date_publication).ToListAsync();
            var lista = new List<GetPublicationModel>();
            foreach (var publication in publications)
            {
                var getPublication = new GetPublicationModel
                    (
                        publication.id_publication,
                        publication.user_id,
                        publication.text_publication,
                        publication.url_image_one_publication,
                        publication.url_image_two_publication,
                        publication.url_image_three_publication,
                        publication.url_image_four_publication,
                        publication.date_publication,
                        publication.comunity_id,
                        publication.place_id,
                        publication.event_id
                    );
                lista.Add(getPublication);
            }
            return Ok(lista);
        }

        [HttpGet("comunities/{id}")]
        public async Task<IActionResult> getPublicationComunity(int id)
        {
            var teste = new PlaceController();
            var publications = await context.publications.Where(publication => publication.activated_publication == true && publication.comunity_id == id).OrderByDescending(publication => publication.date_publication).ToListAsync();
            var lista = new List<GetPublicationModel>();
            foreach (var publication in publications)
            {
                var getPublication = new GetPublicationModel
                    (
                        publication.id_publication,
                        publication.user_id,
                        publication.text_publication,
                        publication.url_image_one_publication,
                        publication.url_image_two_publication,
                        publication.url_image_three_publication,
                        publication.url_image_four_publication,
                        publication.date_publication,
                        publication.comunity_id,
                        publication.place_id,
                        publication.event_id
                    );
                lista.Add(getPublication);
            }
            return Ok(lista);
        }

        [HttpGet("user/{user_id}/desactived")]
        public async Task<IActionResult> getUserPublicationsDesactived(int user_id)
        {
            var teste = new PlaceController();
            var publications = await context.publications.Where(publication => publication.user_id == user_id && publication.activated_publication == false).OrderByDescending(user => user.date_publication).ToListAsync();
            var lista = new List<GetPublicationModel>();
            foreach (var publication in publications)
            {
                var getPublication = new GetPublicationModel
                    (
                        publication.id_publication,
                        publication.user_id,
                        publication.text_publication,
                        publication.url_image_one_publication,
                        publication.url_image_two_publication,
                        publication.url_image_three_publication,
                        publication.url_image_four_publication,
                        publication.date_publication,
                        publication.comunity_id,
                        publication.place_id,
                        publication.event_id
                    );
                lista.Add(getPublication);
            }
            return Ok(lista);
        }

        [HttpPut("arquivar")]
        public async Task<IActionResult> arquivar(int id_publication)
        {
            var publication = await context.publications.SingleOrDefaultAsync(publication => publication.id_publication == id_publication);
            publication.activated_publication = false;
            await context.SaveChangesAsync();
            return Ok();
        }
        [HttpGet("user/{id_user}/{text_publication}")]
        public async Task<IActionResult> searchPublication(int id_user, string text_publication)
        {
            var teste1 = await context.publications.Where(publication => publication.user_id == id_user && publication.text_publication.Contains(text_publication)).ToListAsync();
            return Ok(teste1);
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

        [HttpGet("seguindo/{id}")]
        public async Task<IActionResult> getPublicationsSeguindo(int id)
        {
            var publis = await (
            from publication in context.publications
            join user_follow in context.user_follows
            on publication.user_id equals user_follow.follow_id
            where user_follow.user_id == id && publication.activated_publication == true
            orderby publication.date_publication descending
            select new 
            {
                publication.id_publication,
                publication.user_id,
                publication.text_publication,
                publication.url_image_one_publication,
                publication.url_image_two_publication,
                publication.url_image_three_publication,
                publication.url_image_four_publication,
                publication.date_publication,
                publication.comunity_id,
                publication.place_id,
                publication.event_id
            }
            ).ToListAsync();
            return Ok(publis);
        }
        [HttpGet("search/{conteudo}")]
        public async Task<IActionResult> getPublicationsSeach(string conteudo)
        {
            var publis = await (
            from publication in context.publications
            where publication.activated_publication == true && publication.text_publication.Contains(conteudo)
            orderby publication.date_publication descending
            select new
            {
                publication.id_publication,
                publication.user_id,
                publication.text_publication,
                publication.url_image_one_publication,
                publication.url_image_two_publication,
                publication.url_image_three_publication,
                publication.url_image_four_publication,
                publication.date_publication,
                publication.comunity_id,
                publication.place_id,
                publication.event_id
            }
            ).ToListAsync();
            return Ok(publis);
        }
        [HttpPost("testes")]
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
    }
}
