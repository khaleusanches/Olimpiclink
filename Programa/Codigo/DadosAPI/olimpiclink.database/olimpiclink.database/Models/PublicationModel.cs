using Org.BouncyCastle.Utilities;
using System.ComponentModel.DataAnnotations;

namespace olimpiclink.database.Models
{
    public class PublicationModel
    {
        [Key]
        public int id_publication { get; set; }
        public int user_id { get; set; }
        public string text_publication { get; set; }
        public byte[]? image_one_publication { get; set; }
        public byte[]? image_two_publication { get; set; }
        public byte[]? image_three_publication { get; set; }
        public byte[]? image_four_publication { get; set; }
        public DateTime date_publication { get; set; }
        public int? comunity_id { get; set; }
        public int? place_id { get; set; }
        public int? event_id { get; set; }

        public PublicationModel(int id_publication, int user_id, string text_publication, DateTime date_publication)
        {
            this.id_publication = id_publication;
            this.user_id = user_id;
            this.text_publication = text_publication;
            this.date_publication = date_publication;
        }
    }
}
