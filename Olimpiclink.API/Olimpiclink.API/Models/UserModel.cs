namespace Olimpiclink.API.Models
{
    public class UserModel
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Email { get; set; }
        public string Password { get; set; }
        public string UserName { get; set; }
        public bool Verificado_Email { get; set; } = false;
        public string number { get; set; }
    }
}
