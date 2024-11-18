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
        [HttpGet("{id}")]
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
    }
}
