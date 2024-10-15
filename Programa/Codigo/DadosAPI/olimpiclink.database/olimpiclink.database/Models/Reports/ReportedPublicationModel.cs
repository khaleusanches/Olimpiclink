using System.ComponentModel.DataAnnotations;

namespace olimpiclink.database.Models.Reports
{
    public class ReportedPublicationModel
    {
        [Key]
        public int id_report_publication {  get; set; }
        public int publication_id { get; set; }
        public int user_id { get; set; }
        public string reason { get; set; }
        public ReportedPublicationModel(int publication_id, int user_id, string reason) 
        {
            this.publication_id = publication_id;
            this.user_id = user_id;
            this.reason = reason;
        }
    }
}
