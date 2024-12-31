using System.ComponentModel.DataAnnotations;

namespace olimpiclink.database.Models.pictures_events
{
    public class GetPictureEventModel
    {
        [Key]
        public int id_picture_event { get; set; }
        public int picture_event_id { get; set; }
        public byte[] archive_picture_event { get; set; }
        public GetPictureEventModel(int id_picture_event, int picture_event_id, byte[] archive_picture_event)
        {
            this.id_picture_event = id_picture_event;
            this.picture_event_id = picture_event_id;
            this.archive_picture_event = archive_picture_event;
        }
    }
}
