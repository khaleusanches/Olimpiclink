using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Data;
using olimpiclink.database.Models.Reports;
using System.Text.RegularExpressions;

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
            var list_reported_events = await (
                from reported_events in context.reported_events
                where reported_events.report_read == false
                group reported_events by reported_events.event_id into group_event
                orderby group_event.Count() descending
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
                            comunity_denuns = (
                                from reported_events in context.reported_events
                                where events.comunity_id == comunities.id_comunity
                                select reported_events.id_report_publication
                                ).Count()
                        }
                        ).ToList(),
                    reports = (
                        from reported_events in context.reported_events
                        where reported_events.event_id == group_event.Key
                        select new
                        {
                            reported_events.id_report_publication,
                            reported_events.event_id,
                            reported_events.user_id,
                            reported_events.reason
                        }).ToList()
                }
                ).ToListAsync();
            return Ok(list_reported_events);
        }
        [HttpPut("read")]
        public async Task<IActionResult> markRead(int id_event)
        {
            var list_reports = await context.reported_events.Where(report => report.event_id == id_event).ToListAsync();
            foreach(var report in  list_reports) 
            {
                report.report_read = true;
            }
            await context.SaveChangesAsync();
            return Ok();
        }

        [HttpPut("archive")]
        public async Task<IActionResult> archive(int id_event)
        {
            var list_reports = await context.reported_events.Where(report => report.event_id == id_event).ToListAsync();
            foreach (var report in list_reports)
            {
                report.report_read = true;
            }
            await context.SaveChangesAsync();
            new EventController().archive(id_event);
            return Ok();
        }
    }
}
