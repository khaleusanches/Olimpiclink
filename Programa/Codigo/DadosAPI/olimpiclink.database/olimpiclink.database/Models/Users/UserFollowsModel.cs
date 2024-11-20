using System.ComponentModel.DataAnnotations;

namespace olimpiclink.database.Models.Users
{
    public class UserFollowsModel
    {
        [Key]
        public int id_user_follow { get; set; }
        public int user_id { get; set; }
        public int follow_id { get; set; }
        public UserFollowsModel(int user_id, int follow_id) 
        {
            this.user_id = user_id;
            this.follow_id = follow_id;
        }
    }
}
