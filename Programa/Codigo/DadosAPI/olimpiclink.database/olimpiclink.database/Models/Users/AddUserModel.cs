namespace olimpiclink.database.Models.Users
{
    public class AddUserModel
    {
        public string name_user { get; set; }
        public string email_user { get; set; }
        public string password_user { get; set; }
        public string login_user { get; set; }
        public string city_name { get; set; }
        public string[] categories { get; set; }
    }
}