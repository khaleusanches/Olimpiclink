using System.ComponentModel.DataAnnotations;

namespace olimpiclink.database.Models.Comunities
{
    public class LeaderModel
    {
        [Key]
        public int id_leader { get; set; }
        public int user_id { get; set; }
        public LeaderModel(int user_id) 
        {
            this.user_id = user_id;
        }
    }
}
