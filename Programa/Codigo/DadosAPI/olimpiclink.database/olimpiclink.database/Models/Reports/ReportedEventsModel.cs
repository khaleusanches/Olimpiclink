using System.ComponentModel.DataAnnotations;

namespace olimpiclink.database.Models.Reports
{
    public class ReportedEventsModel
    {
        [Key]
        public int id_report_publication { get; set; }
        public int event_id { get; set; }
        public int user_id { get; set; }
        public string reason { get; set; }
        public DateTime created_at_report_event { get; set; }
        public bool report_read { get; set; }
        public ReportedEventsModel(int event_id, int user_id, string reason)
        {
            this.event_id = event_id;
            this.user_id = user_id;
            this.reason = reason;
            var data = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            created_at_report_event = DateTime.ParseExact(data, "yyyy-MM-dd HH:mm:ss", null);
            report_read = false;
        }
    }
}
