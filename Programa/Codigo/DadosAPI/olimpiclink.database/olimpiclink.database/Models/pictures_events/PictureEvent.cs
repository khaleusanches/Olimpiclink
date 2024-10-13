using System.ComponentModel.DataAnnotations;

namespace olimpiclink.database.Models.pictures_events
{
    public class PictureEvent
    {
        [Key]
        public int id_picture_event { get; set; }
        public int event_id { get; set;}
        public byte[] archive_picture_event { get; set; }
        public PictureEvent(int event_id, byte[] archive_picture_event) 
        {
            this.event_id = event_id;
            this.archive_picture_event = archive_picture_event;
        }
    }
}
