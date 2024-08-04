using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Models;
using Pomelo.EntityFrameworkCore.MySql;
using System.Configuration;

namespace olimpiclink.database.Data
{
    public class ConnectionContext : DbContext
    {
        public DbSet<CategoryModel> Categories { get; set; }
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseMySql("Server=localhost;Port=3306;Database=olimpiclink2;Uid=root;Pwd=123;", new MySqlServerVersion(new Version(8, 0, 21)));
        }
    }
}
