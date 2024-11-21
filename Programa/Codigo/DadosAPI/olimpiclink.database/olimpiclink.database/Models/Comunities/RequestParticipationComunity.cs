using System.ComponentModel.DataAnnotations;

namespace olimpiclink.database.Models.Comunities
{
    public class RequestParticipationComunity
    {
        [Key]
        public int request_participation_comunity { get; set; }
        public int user_id { get; set; }
        public int comunity_id { get; set; }
        public bool analisado { get; set; }
        public bool? acepted { get; set; }

        public RequestParticipationComunity
            (
            int user_id,
            int comunity_id,
            bool analisado = false,
            bool? acepted = null
            )
        {
            this.user_id = user_id;
            this.comunity_id = comunity_id;
            this.analisado = analisado;
            this.acepted = acepted;
        }
    }
}
