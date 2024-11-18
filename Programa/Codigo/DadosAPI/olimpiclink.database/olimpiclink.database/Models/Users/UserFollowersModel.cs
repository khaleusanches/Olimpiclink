using System.ComponentModel.DataAnnotations;

namespace olimpiclink.database.Models.Users
{
    public class UserFollowersModel
    {
        [Key]
        public int user_id { get; set; }
        public int follower_id { get; set; }
    }
}
