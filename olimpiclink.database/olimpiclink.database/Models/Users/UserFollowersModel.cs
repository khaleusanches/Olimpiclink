using System.ComponentModel.DataAnnotations;

namespace olimpiclink.database.Models.Users
{
    public class UserFollowersModel
    {
        [Key]
        public int id_user_follower { get; set; }
        public int user_id { get; set; }
        public int follower_id { get; set; }
        public UserFollowersModel(int user_id, int follower_id) 
        {
            this.user_id = user_id;
            this.follower_id = follower_id;
        }
    }
}
