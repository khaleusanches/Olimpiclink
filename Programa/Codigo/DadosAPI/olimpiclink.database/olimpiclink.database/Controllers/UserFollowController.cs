using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Data;
using olimpiclink.database.Models.Users;

namespace olimpiclink.database.Controllers
{
    [ApiController]
    [Route("api/follows")]
    public class UserFollowController : Controller
    {
        ConnectionContext context = new ConnectionContext();
        [HttpGet("/api/FFF/{id}")]
        public async Task<IActionResult> getFriendsFollowsFollowers(int id)
        {
            var qtd_friends = await (
                from user_follows in context.user_follows
                join user_followers in context.user_followers
                on user_follows.user_id equals user_followers.user_id
                where user_follows.follow_id == user_followers.follower_id && user_followers.user_id == id
                select new
                {
                    user_followers.user_id
                }
                ).ToListAsync();
            var qtd_follows = await (
                from user_follows in context.user_follows
                where user_follows.user_id == id
                select new
                {
                    user_follows.user_id
                }
                ).ToListAsync();
            var qtd_followers = await (
                from user_followers in context.user_followers
                where user_followers.user_id == id
                select new
                {
                    user_followers.user_id
                }
                ).ToListAsync();

            return Ok(new
            {
                friends = qtd_friends.Count(),
                follows = qtd_follows.Count(),
                followers = qtd_followers.Count()
            });
        }
        [HttpGet("/api/followers/{id}")]
        public async Task<IActionResult> getFollowers(int id)
        {
            var followers = await (
                from f in context.user_followers
                join u in context.users
                on f.follower_id equals u.id_user
                where f.user_id == id
                select new
                {
                    img_profile = u.url_profile_picture_user,
                    login_user = u.login_user,
                    user_id = u.id_user
                }).ToListAsync();
            return Ok(followers);
        }

        [HttpGet("/api/followers/{id}&&{login}")]
        public async Task<IActionResult> getFollowersName(int id, string login)
        {
            var followers = await (
                from f in context.user_followers
                join u in context.users
                on f.follower_id equals u.id_user
                where f.user_id == id && u.login_user.Contains(login)
                select new
                {
                    img_profile = u.url_profile_picture_user,
                    login_user = u.login_user,
                    user_id = u.id_user
                }).ToListAsync();
            return Ok(followers);
        }

        [HttpDelete("/api/followers/{id_user}&&{id}")]
        public async void removeFollower(int id_user, int id)
        {
            var follower = await context.user_followers.SingleOrDefaultAsync(user => user.user_id == id_user && user.follower_id == id);
            context.user_followers.Remove(follower);
            await context.SaveChangesAsync();
        }


        [HttpGet("/api/follows/{id}")]
        public async Task<IActionResult> getFollows(int id)
        {
            var follows = await (
                from f in context.user_follows
                join u in context.users
                on f.follow_id equals u.id_user
                where f.user_id == id
                select new
                {
                    img_profile = u.url_profile_picture_user,
                    login_user = u.login_user,
                    user_id = u.id_user
                }).ToListAsync();
            return Ok(follows);
        }

        [HttpGet("/api/follows/{id}&&{login}")]
        public async Task<IActionResult> getFollowsName(int id, string login)
        {
            var follows = await (
                from f in context.user_follows
                join u in context.users
                on f.follow_id equals u.id_user
                where f.user_id == id && u.login_user.Contains(login)
                select new
                {
                    img_profile = u.url_profile_picture_user,
                    login_user = u.login_user,
                    user_id = u.id_user
                }).ToListAsync();
            return Ok(follows);
        }

        [HttpDelete("/api/follows/{id_user}&&{id}")]
        public async void removeFollow(int id_user, int id)
        {
            try
            {
                var follow = await context.user_follows.SingleOrDefaultAsync(user => user.user_id == id_user & user.follow_id == id);
                var follower = await context.user_followers.SingleOrDefaultAsync(user => user.user_id == id && user.follower_id == id_user);
                context.user_followers.Remove(follower);
                context.user_follows.Remove(follow);
                await context.SaveChangesAsync();
            }
            catch { }

        }

        [HttpGet("/api/friends/{id}")]
        public async Task<IActionResult> getFriends(int id)
        {
            var friends = await (
                from user_follows in context.user_follows
                join user_followers in context.user_followers
                on user_follows.user_id equals user_followers.user_id
                join user in context.users
                on user_follows.follow_id equals user.id_user
                where user_follows.follow_id == user_followers.follower_id && user_followers.user_id == id
                select new
                {
                    img_profile = user.url_profile_picture_user,
                    login_user = user.login_user,
                    user_id = user.id_user
                }).ToListAsync();
            return Ok(friends);
        }

        [HttpGet("/api/friends/{id}&&{login}")]
        public async Task<IActionResult> getFriendsName(int id, string login)
        {
            var friends = await (
                from user_follows in context.user_follows
                join user_followers in context.user_followers
                on user_follows.user_id equals user_followers.user_id
                join user in context.users
                on user_follows.follow_id equals user.id_user
                where user_follows.follow_id == user_followers.follower_id && user_followers.user_id == id && user.login_user.Contains(login)
                select new
                {
                    img_profile = user.url_profile_picture_user,
                    login_user = user.login_user,
                    user_id = user.id_user
                }).ToListAsync();
            return Ok(friends);
        }

        [HttpDelete("/api/friends/{id_user}&&{id}")]
        public void removeFriends(int id_user, int id)
        {
            try
            {
                var follow = context.user_follows.SingleOrDefault(user => user.user_id == id_user && user.follow_id == id);
                var followers = context.user_followers.SingleOrDefault(user => user.user_id == id_user && user.follower_id == id);
                context.user_follows.Remove(follow);
                context.user_followers.Remove(followers);
                context.SaveChanges();
            }
            catch { }
        }

        [HttpGet("/api/FFF/{id_user}&&{id}")]
        public async Task<IActionResult> getFollowInterrogation(int id_user, int id)
        {
            var msg = new { message = ""};
            var follow = await (
                from user_follows in context.user_follows
                where user_follows.follow_id == id && user_follows.user_id == id_user
                select new
                {
                    msg = "Seguindo"
                }).SingleOrDefaultAsync();
            var follower = await (
                from user_follows in context.user_follows
                where user_follows.follow_id == id_user && user_follows.user_id == id
                select new
                {
                    msg = "Seguidor"
                }).SingleOrDefaultAsync();
            if(follower != null && follow != null)
            {
                msg = new { message = "amigos" };
            }
            else if(follower != null)
            {
                msg = new { message = "seguidor" };
            }
            else if(follow != null)
            {
                msg = new { message = "seguindo" };
            }
            else
            {
                msg = new { message = "seguir" };
            }
            return Ok(msg);
        }

        [HttpPost("/api/follows/seguir")]
        public async void seguirUser(UserFollowsModel new_user_follow)
        {
            try
            {
                var new_user_follower = new UserFollowersModel(new_user_follow.follow_id, new_user_follow.user_id);
                await context.user_follows.AddAsync(new_user_follow);
                await context.user_followers.AddAsync(new_user_follower);
                await context.SaveChangesAsync();
            }
            catch { }
        }
    }
}
