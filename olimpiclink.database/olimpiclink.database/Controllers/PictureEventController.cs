using Microsoft.AspNetCore.Mvc;
using olimpiclink.database.Data;
using olimpiclink.database.Models.pictures_events;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace olimpiclink.database.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PictureEventController : ControllerBase
    {
        ConnectionContext context = new ConnectionContext();
        // GET api/<PictureEventController>/5
        [HttpGet("{id}")]
        public async Task<IActionResult> Get(int id)
        {
            var picture = await context.pictures_events.FindAsync(id);
            if(picture != null)
            {
                return File(picture.archive_picture_event, "image/jpeg");
            }
            return BadRequest();
            
        }

        // POST api/<PictureEventController>
        [HttpPost]
        public async Task<IActionResult> Post(PictureEvent new_picture)
        {
            await context.pictures_events.AddAsync(new_picture);
            await context.SaveChangesAsync();
            return Ok();
        }

        // DELETE api/<PictureEventController>/5
        [HttpDelete("{id}")]
        public void Delete(int id)
        {
        }
    }
}
