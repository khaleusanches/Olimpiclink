using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Data;
using olimpiclink.database.Models.Reports;

namespace olimpiclink.database.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ReportedEventsController : ControllerBase
    {
        ConnectionContext context = new ConnectionContext();
        [HttpPost]
        public async Task<IActionResult> post(ReportedEventsModel new_event)
        {
            var old_report = await context.reported_events.Where(events => events.user_id == new_event.user_id && events.event_id == new_event.event_id).ToListAsync();
            if(old_report.Count() > 0)
            {

                return BadRequest(new {msg = "Uma denúncia a este evento já foi registrada"});
            }
            await context.reported_events.AddAsync(new_event);
            await context.SaveChangesAsync();
            return Ok();
        }

        [HttpGet]
        public async Task<IActionResult> get()
        {
            var list_reported_events = (
                from reported_events in context.reported_events
                group reported_events by reported_events.event_id into group_event
                select new
                {
                    event_id = group_event.Key,
                    event_denuns = group_event.Count(),
                    reported_event = (
                        from events in context.events

                        join comunities in context.comunities
                        on events.comunity_id equals comunities.id_comunity

                        join places in context.places
                        on events.place_id equals places.id_place

                        join comunities_has_leader in context.comunity_has_leader
                        on comunities.id_comunity equals comunities_has_leader.comunity_id

                        join leaders in context.leaders
                        on comunities_has_leader.leader_id equals leaders.id_leader

                        join users in context.users
                        on leaders.user_id equals users.id_user
                        where events.idEvent == group_event.Key
                        select new
                            {
                                comunities.id_comunity,
                                comunities.name_comunity,
                                events.nameEvent,
                                events.created_at_event,
                                events.descriptionEvent,
                                events.dateTimeEvent,
                                events.closingDateTimeEvent,
                                places.name_place,

                                leader_name = users.name_user,
                                leader_email = users.email_user,
                                event_denuns = group_event.Count()

                        }
                        ).ToList(),
                }
                );
            return Ok(list_reported_events);
        }
    }
}
