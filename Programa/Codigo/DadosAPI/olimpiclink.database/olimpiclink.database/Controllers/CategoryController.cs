using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Data;
using olimpiclink.database.Models;
using olimpiclink.database.Models.Categories;
using olimpiclink.database.Models.Users;
using System.Linq;

namespace olimpiclink.database.Controllers
{
    [ApiController] 
    [Route("api/category")]  // define a rota da url
    public class CategoryController : Controller
    {
        ConnectionContext context = new ConnectionContext();
        [HttpPost]
        public async Task<IActionResult> categoryCreate(CategoryModel new_category)
        {
            if(await context.categories.AnyAsync(category => category.name_category == new_category.name_category))
            {
                return Ok("Essa categoria já existe");
            }
            await context.categories.AddAsync(new_category); //adiciona no banco de dados
            await context.SaveChangesAsync(); //salva no banco
            return Ok();
        }

        [HttpGet("{id_category}")] //Define que o metodo a seguir é GET, usado para pegar um dado especifico
        public async Task<IActionResult> categoryGetID(long? id_category)
        {
             return Ok(await context.categories.Where(category => category.id_category == id_category).ToListAsync()); //puxa a categoria que tenha o id referente ao id inserido na url
        }

        [HttpGet] // Define que o metodo a seguir é GET, usando para pegar todos os dados da tabela.
        public async Task<IActionResult> categoryGet()
        {
            var get_categories = await context.categories.ToListAsync();
            return Ok(get_categories);
        }

        [HttpGet("name/{name_category}")]
        public int categoryGetName(string name_category)
        {
            var id_category = context.categories.SingleOrDefault(category => category.name_category == name_category).id_category;
            return id_category;
        }

        [HttpPost("userCategory")]
        public async Task<IActionResult> postUserCategory(string login_user, int id_category)
        {
            var user = context.users.FirstOrDefaultAsync(user => user.login_user == login_user);
            var new_user_category = new UserCategory(user.Result.id_user, id_category);
            await context.user_category.AddAsync(new_user_category);
            await context.SaveChangesAsync();
            return Ok();
        }

        [HttpGet("delete/{id_category}")]
        public async Task<IActionResult> categoryDelete(long? id_category, CancellationToken ct)
        {
            var category = await context.categories.SingleOrDefaultAsync(category => category.id_category == id_category, ct);
            if (category == null)
            {
                return Ok("Categoria não encontrada");
            }
            context.categories.Remove(category);
            await context.SaveChangesAsync(ct);
            return Ok();
        }
    }
}
