using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Models.Categories;
using olimpiclink.database.Models.Cities;
using olimpiclink.database.Models.Comunities;
using olimpiclink.database.Models.Events;
using olimpiclink.database.Models.pictures_events;
using olimpiclink.database.Models.Places;
using olimpiclink.database.Models.Publications;
using olimpiclink.database.Models.Reports;
using olimpiclink.database.Models.Users;
using Pomelo.EntityFrameworkCore.MySql;
using System.Configuration;

namespace olimpiclink.database.Data
{
    public class ConnectionContext : DbContext
    {
        public DbSet<CategoryModel> categories { get; set; }
        public DbSet<UserModel> users { get; set; }
        public DbSet<City> cities { get; set; }
        public DbSet<ComunityModel> comunities { get; set; }
        public DbSet<PublicationModel> publications { get; set; }
        public DbSet<PlaceModel> places { get; set; }
        public DbSet<EventModel> events { get; set; }
        public DbSet<LeaderModel> leaders { get; set; }
        public DbSet<ComunityHasLeader> comunity_has_leader { get; set; }
        public DbSet<PictureEvent> pictures_events { get; set; }
        public DbSet<MarkedPresenceModel> marked_presences { get; set; }
        public DbSet<ReportedPublicationModel> reported_publications { get; set; }
        public DbSet<ReportedEventsModel> reported_events {  get; set; }
        public DbSet<UserComunityModel> user_comunity { get; set; }
        public DbSet<UserFollowsModel> user_follows { get; set; }
        public DbSet<UserFollowersModel> user_followers { get; set; }
        public DbSet<UserCity> user_city { get; set; }
        public DbSet<UserCategory> user_category { get; set; }
        public DbSet<ParticipationComunity> participation_comunity { get; set; }
        public DbSet<FollowComunity> follow_comunity { get; set; }
        public DbSet<RequestParticipationComunity> request_participation_comunity { get; set; }
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseMySql("Server=localhost;Port=3306;Database=olimpiclink;Uid=root;Pwd=123;", new MySqlServerVersion(new Version(8, 0, 21)));
        }
    }
}
