using System.ComponentModel.DataAnnotations;

namespace olimpiclink.database.Models.Users
{
    public class UserCity
    {
        [Key]
        public int user_id { get; set; }
        public int city_id { get; set; }
        public UserCity(int user_id, int city_id) 
        {
            this.user_id = user_id;
            this.city_id = city_id;
        }
    }
}
