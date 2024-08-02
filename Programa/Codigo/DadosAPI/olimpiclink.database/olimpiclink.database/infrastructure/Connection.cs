using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Models;
using Pomelo.EntityFrameworkCore.MySql;
using System.Configuration;

namespace olimpiclink.database.infrastructure
{
    public class Connection : DbContext
    {
        public DbSet<Category> Categories { get; set; }
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseMySql("Server=localhost;Port=3306;Database=olimpiclink;Uid=root;Pwd=123;", new MySqlServerVersion(new Version(8,0,21)));
        }
    }
}
