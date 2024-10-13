using System.ComponentModel.DataAnnotations;

namespace olimpiclink.database.Models.Events
{
    public class MarkedPresenceModel
    {
        [Key]
        public int id_marked_presence { get; set; }
        public int user_id { get; set; }
        public int event_id {  get; set; }

        public MarkedPresenceModel(int user_id, int event_id) 
        {
            this.event_id = event_id;
            this.user_id = user_id;
        }
    }
}
