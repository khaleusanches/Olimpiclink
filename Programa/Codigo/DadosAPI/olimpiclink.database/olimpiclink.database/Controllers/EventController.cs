using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;
using olimpiclink.database.Data;
using olimpiclink.database.Models.Comunities;
using olimpiclink.database.Models.Events;
using olimpiclink.database.Models.pictures_events;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace olimpiclink.database.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class EventController : ControllerBase
    {
        ConnectionContext context = new ConnectionContext();
        // GET: api/<EventController>
        [HttpGet]
        public async Task<IActionResult> Get()
        {
            var list = await (
                from events in context.events

                join place in context.places
                on events.place_id equals place.id_place

                join comunity in context.comunities
                on events.comunity_id equals comunity.id_comunity
                where events.activated_event == true
                select new 
                {
                    idEvent = events.idEvent,
                    place_id = events.place_id,
                    place_name = place.name_place,
                    comunity_id = events.comunity_id,
                    comunity_name = comunity.name_comunity,
                    comunity_picture = "http://192.168.0.158:5000/api/comunities/images/"+ comunity.id_comunity,
                    nameEvent = events.nameEvent,
                    descriptionEvent = events.descriptionEvent,
                    dateTimeEvent = events.dateTimeEvent,
                    closingDateTimeEvent = events.closingDateTimeEvent,
                    url_picture_event = (from picture in context.pictures_events
                                         where picture.event_id == events.idEvent
                                         select (picture == null ? null : "http://192.168.0.158:5000/api/PictureEvent/" + picture.id_picture_event)
                                         ).ToList(),
                    marked_presences = (from marked_presences in context.marked_presences
                                        where marked_presences.event_id == events.idEvent
                                        select marked_presences).Count()
                }
            ).ToListAsync();

            if (list == null)
            {
                return BadRequest();
            }
            return Ok(list);
        }

        // GET api/<EventController>/5
        [HttpGet("{id}")]
        public async Task<IActionResult> Get(int id)
        {
            var ev = await (
                from events in context.events
                join place in context.places
                on events.place_id equals place.id_place

                join comunity in context.comunities
                on events.comunity_id equals comunity.id_comunity

                where (events.idEvent == id)
                select new
                {
                    idEvent = events.idEvent,
                    place_id = events.place_id,
                    place_name = place.name_place,
                    comunity_id = events.comunity_id,
                    comunity_name = comunity.name_comunity,
                    comunity_picture = "http://192.168.0.158:5000/api/comunities/images/" + comunity.id_comunity,
                    nameEvent = events.nameEvent,
                    descriptionEvent = events.descriptionEvent,
                    dateTimeEvent = events.dateTimeEvent,
                    closingDateTimeEvent = events.closingDateTimeEvent,
                    url_picture_event = (from picture in context.pictures_events
                                         where picture.event_id == events.idEvent
                                         select (picture == null ? null : "http://192.168.0.158:5000/api/PictureEvent/" + picture.id_picture_event)
                                         ).ToList(),
                    marked_presences = (from marked_presences in context.marked_presences
                                        where marked_presences.event_id == events.idEvent
                                        select marked_presences).Count()
                }
            ).FirstOrDefaultAsync();
            if(ev == null)
            {
                return BadRequest();
            }
            return Ok(ev);
        }

        // POST api/<EventController>
        [HttpPost]
        public async Task<IActionResult> Post(PostEventModel post_new_event)
        {
            EventModel new_event = 
                new EventModel(
                    post_new_event.place_id, 
                    post_new_event.comunity_id, 
                    post_new_event.leader_id,
                    post_new_event.nameEvent, 
                    post_new_event.descriptionEvent, 
                    post_new_event.dateTimeEvent, 
                    post_new_event.closingDateTimeEvent
                    );
            await context.events.AddAsync(new_event);
            await context.SaveChangesAsync();
            var id_event = (await context.events.ToListAsync()).Count();
            if (post_new_event.pictures_event != null)
            {
                foreach (var picture_event in post_new_event.pictures_event)
                {
                    byte[] byte_picture = Convert.FromBase64String(picture_event.Trim().Replace(" ", "").Replace("\n", ""));
                    PictureEvent new_picture_event = new PictureEvent(id_event, byte_picture);
                    await context.pictures_events.AddAsync(new_picture_event);
                    await context.SaveChangesAsync();
                }
            }
            return Ok();
        }

        // PUT api/<EventController>/5
        [HttpPut]
        public async void archive(int id)
        {
            var events = await context.events.FindAsync(id);
            if(events != null)
            {
                events.activated_event = false;
                await context.SaveChangesAsync();
            }

        }
        [HttpGet("mini")]
        public async Task<IActionResult> getEventMini()
        {
            var list = await (
                from events in context.events

                join place in context.places
                on events.place_id equals place.id_place

                join comunity in context.comunities
                on events.comunity_id equals comunity.id_comunity
                where events.activated_event == true
                orderby events.created_at_event descending
                select new
                {
                    idEvent = events.idEvent,
                    comunity_id = events.comunity_id,
                    comunity_name = comunity.name_comunity,
                    comunity_picture = "http://192.168.0.158:5000/api/comunities/images/" + comunity.id_comunity,
                    nameEvent = events.nameEvent,
                    descriptionEvent = events.descriptionEvent,
                    data = events.created_at_event,
                    url_picture_event = (from picture in context.pictures_events
                                         where picture.event_id == events.idEvent
                                         select (picture == null ? null : "http://192.168.0.158:5000/api/PictureEvent/" + picture.id_picture_event)
                                         ).Take(1).ToList(),
                }
            ).ToListAsync();

            if (list == null)
            {
                return BadRequest();
            }
            return Ok(list);
        }
        [HttpGet("mini/{id}")]
        public async Task<IActionResult> getEventMiniSeguindo(int id)
        {
            var list = await (
                from events in context.events
                join place in context.places
                on events.place_id equals place.id_place

                join comunity in context.comunities
                on events.comunity_id equals comunity.id_comunity

                join user_comunity in context.user_comunity
                on comunity.id_comunity equals user_comunity.comunity_id

                where events.activated_event == true && user_comunity.user_id == id
                orderby events.created_at_event descending
                select new
                {
                    idEvent = events.idEvent,
                    comunity_id = events.comunity_id,
                    comunity_name = comunity.name_comunity,
                    comunity_picture = "http://192.168.0.158:5000/api/comunities/images/" + comunity.id_comunity,
                    nameEvent = events.nameEvent,
                    descriptionEvent = events.descriptionEvent,
                    data = events.created_at_event,
                    url_picture_event = (from picture in context.pictures_events
                                         where picture.event_id == events.idEvent
                                         select (picture == null ? null : "http://192.168.0.158:5000/api/PictureEvent/" + picture.id_picture_event)
                                         ).Take(1).ToList(),
                }
            ).ToListAsync();

            if (list == null)
            {
                return BadRequest();
            }
            return Ok(list);
        }
    }
}
