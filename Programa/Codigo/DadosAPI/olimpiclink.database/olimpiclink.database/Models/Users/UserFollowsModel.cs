using System.ComponentModel.DataAnnotations;

namespace olimpiclink.database.Models.Users
{
    public class UserFollowsModel
    {
        [Key]
        public int user_id { get; set; }
        public int follow_id { get; set; }
    }
}
