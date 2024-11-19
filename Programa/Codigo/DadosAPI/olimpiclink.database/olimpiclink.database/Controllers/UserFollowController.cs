using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Data;

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
            
        [HttpDelete("/api/followers/{id_user}&&{id}")]
        public async void removeFollower(int id_user, int id)
        {
            var follower = await context.user_followers.SingleOrDefaultAsync(user => user.user_id == id_user && user.follower_id == id);
            context.user_followers.Remove(follower);
            await context.SaveChangesAsync();
        }
    }
}
