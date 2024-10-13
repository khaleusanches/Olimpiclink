using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Data;
using olimpiclink.database.Models.Events;

namespace olimpiclink.database.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class MarkedPresenceController : ControllerBase
    {
        ConnectionContext context = new ConnectionContext();
        [HttpPost]
        public async Task<IActionResult> Post(MarkedPresenceModel markedPresences)
        {
            var teste = await context.marked_presences.Where(markedPresence => markedPresence.user_id == markedPresences.user_id && markedPresence.event_id == markedPresences.event_id).ToListAsync();
            if(teste.Count() > 0)
            {
                return BadRequest("O usuario já marcou presença neste evento");
            }
            await context.marked_presences.AddAsync(markedPresences);
            await context.SaveChangesAsync();
            return Ok();
        }
    }
}
