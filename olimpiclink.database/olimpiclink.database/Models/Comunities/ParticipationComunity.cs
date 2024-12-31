using System.ComponentModel.DataAnnotations;

namespace olimpiclink.database.Models.Comunities
{
    public class ParticipationComunity
    {
        [Key]
        public int id_participation_comunity { get; set; }
        public int user_id { get; set; }
        public int comunity_id { get; set; }
        public ParticipationComunity(int user_id, int comunity_id) 
        {
            this.user_id = user_id;
            this.comunity_id = comunity_id;
        }
    }
}
