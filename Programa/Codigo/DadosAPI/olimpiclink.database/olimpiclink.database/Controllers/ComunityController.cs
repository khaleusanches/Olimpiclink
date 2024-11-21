using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Data;
using olimpiclink.database.Models.Comunities;

namespace olimpiclink.database.Controllers
{
    [ApiController]
    [Route("api/comunities")]  //define a rota da url
    public class ComunityController : Controller
    {
        ConnectionContext context = new ConnectionContext(); //cria conexão
        [HttpPost]
        public async void postComunity(ComunityModel new_comunity)
        {
            await context.comunities.AddAsync(new_comunity);
            await context.SaveChangesAsync();
        }

        [HttpGet]
        public async Task<IActionResult> getAllComunity()
        {
            var comunity = await 
                (
                from comunities in context.comunities
                select new
                {
                    comunities.id_comunity,
                    comunities.name_comunity,
                    comunities.description_comunity,
                    comunities.url_icon_comunity,
                    comunities.regras_comunity,
                    n_seguidores = (from follows_comunities in context.follow_comunity
                                    where follows_comunities.comunity_id == comunities.id_comunity
                                    select new {comunities.id_comunity}).Count(),
                    n_participantes = (from participations_comunities in context.participation_comunity
                                       where participations_comunities.comunity_id == comunities.id_comunity
                                       select new { comunities.id_comunity }).Count(),
                    category_icon = (from category in context.categories
                                     where category.id_category == comunities.category_id
                                     select category.url_icon_category).SingleOrDefault(),

                }).ToListAsync();
            return Ok(comunity);
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> getComunityID(int id)
        {
            var comunity = await
                (
                from comunities in context.comunities
                where comunities.id_comunity == id
                select new
                {
                    comunities.id_comunity,
                    comunities.name_comunity,
                    comunities.description_comunity,
                    comunities.url_icon_comunity,
                    comunities.regras_comunity,
                    n_seguidores = (from follows_comunities in context.follow_comunity
                                    where follows_comunities.comunity_id == comunities.id_comunity
                                    select new { comunities.id_comunity }).Count(),
                    n_participantes = (from participations_comunities in context.participation_comunity
                                       where participations_comunities.comunity_id == comunities.id_comunity
                                       select new { comunities.id_comunity }).Count(),
                    category_icon = (from category in context.categories
                                     where category.id_category == comunities.category_id
                                     select category.url_icon_category).SingleOrDefault(),

                }).SingleOrDefaultAsync();
            return Ok(comunity);
        }

        [HttpGet("FF/{id_user}&&{id_comunity}")]
        public async Task<IActionResult> getFollow(int id_user, int id_comunity)
        {
            var msg = new { message = "" };
            var follow = await
                (from comunities in context.comunities
                join follows_comunities in context.follow_comunity
                on comunities.id_comunity equals follows_comunities.comunity_id
                where comunities.id_comunity == id_comunity && follows_comunities.user_id == id_user
                 select new
                 {
                     comunities.id_comunity
                 }).ToListAsync();
            var participation = await
                (from comunities in context.comunities
                 join participations_comunities in context.participation_comunity
                 on comunities.id_comunity equals participations_comunities.comunity_id
                 where comunities.id_comunity == id_comunity && participations_comunities.user_id == id_user
                 select new
                 {
                     comunities.id_comunity
                 }).ToListAsync();
            if (follow.Count() > 0 && participation.Count() == 0)
            {
                msg = new { message = "Segue" };
            }
            else if (participation.Count() > 0)
            {
                msg = new { message = "Participa" };
            }
            else
            {
                msg = new { message = "Seguir" };
            }
            return Ok(msg);
        }

        [HttpGet("RP/{id_user}&&{id_comunity}")]
        public async Task<IActionResult> getRequestParticipation(int id_user, int id_comunity)
        {
            var msg = new { message = "" };
            var participation = await
                        (from comunities in context.comunities
                        join request_participations_comunities in context.request_participation_comunity
                        on comunities.id_comunity equals request_participations_comunities.comunity_id
                        where comunities.id_comunity == id_comunity && request_participations_comunities.user_id == id_user && request_participations_comunities.analisado == false
                        select new
                        {
                            comunities.id_comunity
                        }).ToListAsync();

            if (participation.Count() == 0)
            {
                msg = new { message = "Pedir" };
            }
            else if (participation.Count() > 0)
            {
                msg = new { message = "Aguarde" };
            }
            return Ok(msg);
        }

        [HttpPost("follow")]
        public async void followComunity(FollowComunity new_follow)
        {
            try
            {
                await context.follow_comunity.AddAsync(new_follow);
                await context.SaveChangesAsync();
            }
            catch
            {

            }
        }
        [HttpDelete("desFollow/{id_user}&&{id_comunity}")]
        public void desFollowComunity(int id_user, int id_comunity)
        {
            try
            {
                var follow = context.follow_comunity.Where(fc => fc.user_id == id_user && fc.comunity_id == id_comunity).SingleOrDefault();
                context.follow_comunity.Remove(follow);
                context.SaveChanges();
            }
            catch { }
        }

        [HttpPost("requestParticipation")]
        public async void postRequestComunity(RequestParticipationComunity new_request)
        {
            try
            {
                await context.request_participation_comunity.AddAsync(new_request);
                await context.SaveChangesAsync();
            }
            catch { }
        }

    }
}
