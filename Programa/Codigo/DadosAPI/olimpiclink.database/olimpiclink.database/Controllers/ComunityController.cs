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
        ConnectionContext context = new ConnectionContext(); // Cria conexão

        [HttpPost]
        public async void postComunity(AddComunityModel new_comunity)
        {
            try
            {
                var img_icon_base64 = new_comunity.icon_comunity.Trim().Replace(" ", "").Replace("\n", "");
                var img_banner_base64 = new_comunity.banner_comunity.Trim().Replace(" ", "").Replace("\n", "");
                img_icon_base64 = AdjustBase64Padding(img_icon_base64);
                img_banner_base64 = AdjustBase64Padding(img_banner_base64);

                var img_icon = Convert.FromBase64String(img_icon_base64);
                var img_banner = Convert.FromBase64String(img_banner_base64);

                var new_comu = new ComunityModel(
                    new_comunity.name_comunity,
                    new_comunity.description_comunity,
                    new_comunity.category_id,
                    new_comunity.regras_comunity,
                    img_icon, img_banner
                );
                var id = context.comunities.ToListAsync().Result.Count() + 1;
                new_comu.url_icon_comunity = "http://192.168.0.158:5000/api/comunities/icon/" + id;
                new_comu.url_banner_comunity = "http://192.168.0.158:5000/api/comunities/banner/" + id;
                await context.comunities.AddAsync(new_comu);
                await context.SaveChangesAsync();
                await context.leaders.AddAsync(new LeaderModel(new_comunity.id_user));
                await context.SaveChangesAsync();
                var id_leader = context.leaders.ToListAsync().Result.Count();
                await context.comunity_has_leader.AddAsync(new ComunityHasLeader(id, id_leader));
                await context.SaveChangesAsync();
                await context.participation_comunity.AddAsync(new ParticipationComunity(new_comunity.id_user, id));
                await context.SaveChangesAsync();
                await context.follow_comunity.AddAsync(new FollowComunity(new_comunity.id_user, id));
                await context.SaveChangesAsync();
            }
            catch (FormatException ex)
            {
                // Lidar com erro de formato Base64
                Console.WriteLine("Erro ao converter Base64: " + ex.Message);
            }
        }

        // Função para ajustar o padding da Base64
        private string AdjustBase64Padding(string base64String)
        {
            int mod4 = base64String.Length % 4;
            if (mod4 > 0)
            {
                base64String = base64String.PadRight(base64String.Length + (4 - mod4), '=');
            }
            return base64String;
        }


        [HttpGet("icon/{id}")]
        public async Task<IActionResult> getIconComunity(int id)
        {
            byte[]? publicationImage = (await context.comunities.FindAsync(id)).icon_comunity;
            if (publicationImage == null)
            {
                return BadRequest();
            }
            return File(publicationImage, "image/jpeg");
        }
        [HttpGet("banner/{id}")]
        public async Task<IActionResult> getBannerComunitu(int id)
        {
            byte[]? publicationImage = (await context.comunities.FindAsync(id)).banner_comunity;
            if (publicationImage == null)
            {
                return BadRequest();
            }
            return File(publicationImage, "image/jpeg");
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

        [HttpGet("user/{id_user}")]
        public async Task<IActionResult> getAllComunityUser(int id_user)
        {
            var comunity = await
                (
                from comunities in context.comunities
                join follows_comunities in context.follow_comunity
                on comunities.id_comunity equals follows_comunities.comunity_id
                join user in context.users
                on follows_comunities.user_id equals user.id_user
                where user.id_user == id_user
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
                }).ToListAsync();
            return Ok(comunity);
        }

        [HttpGet("search/{name}")]
        public async Task<IActionResult> getAllComunitySearch(string name)
        {
            var comunity = await
                (
                from comunities in context.comunities
                join follows_comunities in context.follow_comunity
                on comunities.id_comunity equals follows_comunities.comunity_id
                where comunities.name_comunity.Contains(name)
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
                    comunities.url_banner_comunity,
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

        [HttpGet("card")]
        public async Task<IActionResult> getComunityCardID()
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

        [HttpGet("isleader/{id_user}&&{id_comunity}")]
        public async Task<IActionResult> getIsLeader(int id_user, int id_comunity)
        {
            var leader = await (from leaders in context.leaders
                                join users in context.users
                                on leaders.user_id equals users.id_user
                                join leader_comu in context.comunity_has_leader
                                on leaders.id_leader equals leader_comu.leader_id
                                where leader_comu.comunity_id == id_comunity && leaders.user_id == id_user
                                select ""+ leaders.id_leader).ToListAsync();
            
            return Ok(leader);
        }
    }
}
