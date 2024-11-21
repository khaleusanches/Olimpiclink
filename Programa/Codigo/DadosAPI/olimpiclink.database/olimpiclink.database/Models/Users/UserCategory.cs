using System.ComponentModel.DataAnnotations;

namespace olimpiclink.database.Models.Users
{
    public class UserCategory
    {
        [Key]
        public int id_user_category {  get; set; }
        public int user_id { get; set; }
        public int category_id { get; set; }
        public UserCategory(int user_id, int category_id)
        {
            this.user_id = user_id;
            this.category_id = category_id;
        }
    }
}
