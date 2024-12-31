using System.ComponentModel.DataAnnotations;
using static System.Net.Mime.MediaTypeNames;
using System.Reflection.Metadata;

namespace olimpiclink.database.Models.Comunities
{
    public class ComunityModel
    {
        [Key]
        public int id_comunity { get; set; }
        public string name_comunity { get; set; }
        public string description_comunity { get; set; }
        public byte[]? icon_comunity { get; set; }
        public byte[]? banner_comunity { get; set; }

        public string? url_icon_comunity { get; set; }
        public string? url_banner_comunity { get; set; }
        public int? category_id { get; set; }
        public string? regras_comunity { get; set; }

        public DateTime created_at_comunities { get; set; }
        public DateTime updated_at_comunities { get; set; }
        public ComunityModel
            (
            string name_comunity,
            string description_comunity,
            int? category_id,
            string? regras_comunity,
            byte[]? icon_comunity,
            byte[]? banner_comunity
            ) 
        {
            this.name_comunity = name_comunity;
            this.description_comunity = description_comunity;
            this.category_id = category_id;
            this.regras_comunity = regras_comunity;
            this.icon_comunity = icon_comunity;
            this.banner_comunity = banner_comunity;

            var teste = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
            var test2 = DateTime.ParseExact(teste, "yyyy-MM-dd HH:mm:ss", null);
            created_at_comunities = test2;
            updated_at_comunities = test2;
        }
    }
}
