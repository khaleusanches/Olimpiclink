using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Data;
using olimpiclink.database.Models.Reports;

namespace olimpiclink.database.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ReportedPublicationController : Controller
    {
        ConnectionContext context = new ConnectionContext();

        [HttpPost]
        public async Task<IActionResult> post(ReportedPublicationModel new_report)
        {
            var testeando = await context.reported_publications.Where(reported => reported.user_id == new_report.user_id && reported.publication_id == new_report.publication_id).ToListAsync();
            if(testeando.Count() != 0)
            {
                var msg = new { error = "Já foi realizada uma denúncia referente a esta publicação" };
                return BadRequest(msg);
            }
            await context.reported_publications.AddAsync(new_report);
            await context.SaveChangesAsync();
            return Ok();
        }

        [HttpGet]
        public async Task<IActionResult> Get()
        {
            var list_reports = (
                from reports in context.reported_publications
                where reports.report_read == false
                group reports by reports.publication_id into group_reports
                orderby group_reports.Count() descending
                select new
                {
                    publication_id = group_reports.Key,
                    denuncias_a_publicacao = group_reports.Count(),
                    publication = (
                    from publications in context.publications
                    join users in context.users
                    on publications.user_id equals users.id_user
                    where publications.id_publication == group_reports.Key
                    select new
                    {
                        publications.text_publication,
                        publications.date_publication,
                        publications.id_publication,
                        users.id_user,
                        users.login_user,
                        users.name_user,
                        users.email_user,
                        users.url_profile_picture_user,
                        denuncias_a_user = (
                                           from reporteds in context.reported_publications
                                           join publication in context.publications
                                           on reporteds.publication_id equals publication.id_publication

                                           join users in context.users
                                           on publication.user_id equals users.id_user
                                           where publications.user_id == publication.user_id
                                           select reporteds).Count()
                    }
                    ).ToList(),
                    reportes = (
                        from reportes in context.reported_publications
                        join publications in context.publications
                        on reportes.publication_id equals publications.id_publication

                        join users in context.users
                        on publications.user_id equals users.id_user

                        join users_2 in context.users
                        on reportes.user_id equals users_2.id_user
                        where reportes.publication_id == group_reports.Key
                        select new
                        {
                            reportes.id_report_publication,
                            reportes.reason,
                            reportes.user_id,
                            reportes.created_at_report_publication
                        }
                    ).ToList()
                }
                );;
            return Ok(list_reports);
        }
        [HttpPut("read")]
        public async Task<IActionResult> putRead(int id_publication)
        {
            var list_reports = await context.reported_publications.Where(reports => reports.publication_id == id_publication).ToListAsync();
            foreach(var report in list_reports)
            {
                report.report_read = true;
            }
            await context.SaveChangesAsync();
            return Ok();
        }
        [HttpPut("archive")]
        public async Task<IActionResult> putArchive(int id_publication)
        {
            var list_reports = await context.reported_publications.Where(reports => reports.publication_id == id_publication).ToListAsync();
            foreach (var report in list_reports)
            {
                report.report_read = true;
            }
            await context.SaveChangesAsync();
            await new PublicationController().arquivar(id_publication);
            return Ok();
        }
    }
}
