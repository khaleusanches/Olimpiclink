using Microsoft.EntityFrameworkCore;
using Olimpiclink.API.Models;

namespace Olimpiclink.API.Data
{
    public class AppDbContext : DbContext
    {
        public AppDbContext(DbContextOptions<AppDbContext> op) : base(op){}
        public DbSet<UserModel> Users { get; set; }
    }
}
