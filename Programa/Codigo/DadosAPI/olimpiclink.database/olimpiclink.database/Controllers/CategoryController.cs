using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Data;
using olimpiclink.database.Models;
using olimpiclink.database.Models.Categories;
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
        public async Task<IActionResult> categoryGet(long? id_category)
        {
            var get_categories = await context.categories.ToListAsync();
            return Ok(get_categories);
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
