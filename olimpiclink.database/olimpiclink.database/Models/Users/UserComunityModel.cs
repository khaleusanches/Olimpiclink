using System.ComponentModel.DataAnnotations;

namespace olimpiclink.database.Models.Users
{
    public class UserComunityModel
    {
        [Key]
        public int comunity_id { get; set; }
        public int user_id { get; set; }

        public UserComunityModel(int comunity_id, int user_id)
        {
            this.comunity_id = comunity_id;
            this.user_id = user_id;
        }
    }
}
