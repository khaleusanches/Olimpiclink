using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Data;
using olimpiclink.database.Models;
using olimpiclink.database.Models.Categories;
using System.Linq;

namespace olimpiclink.database.Controllers
{
    [ApiController] 
    [Route("api/v1/category")]  //define a rota da url
    public class CategoryController : Controller
    {
        [HttpPost] //Define que o metodo a seguir é POST, ou seja, ele envia dados, não recebe.
        public async Task<IActionResult> Add(AddCategoryModel request, CancellationToken ct)
        {
            ConnectionContext context = new ConnectionContext(); //Chama o "contexto", a conexão com o banco de dados.

            var new_category = new CategoryModel(request.name_category); //objeto category, porém feito apenas com o nome, sem ID, pois não inserimos ID.

            if(await context.Categories.AnyAsync(category => category.name_category == request.name_category, ct))
            {
                return Ok("vacilo");
            }

            await context.Categories.AddAsync(new_category, ct);
            await context.SaveChangesAsync(ct);
            return Ok();
        }

        [HttpGet] // Define que o metodo a seguir é GET, ou seja, ele recebe dados, não envia.
        public async Task<IActionResult> Get(CancellationToken ct)
        {
            ConnectionContext context = new ConnectionContext();
            // Get com filtro
            //var get_category = await context.Categories.Where(category => category.name_category == "teste").ToListAsync(); 
            // Get all
            var get_categories = await context.Categories.ToListAsync(ct);
            return Ok(get_categories);
        }

        [HttpPut]
        public async Task<IActionResult> Put(long? id, AddCategoryModel request, CancellationToken ct)
        {
            ConnectionContext context = new ConnectionContext();
            var category = await context.Categories.SingleOrDefaultAsync(category => category.id_category == id, ct);
            if (category == null)
            {
                return Ok("Categoria não encontrada");
            }
            category.name_category = request.name_category;
            await context.SaveChangesAsync(ct);
            return Ok();
        }

        [HttpDelete]
        public async Task<IActionResult> Delete(long? id, CancellationToken ct)
        {
            ConnectionContext context = new ConnectionContext();
            var category = await context.Categories.SingleOrDefaultAsync(category => category.id_category == id, ct);
            if (category == null)
            {
                return Ok("Categoria não encontrada");
            }
            context.Categories.Remove(category);
            await context.SaveChangesAsync(ct);
            return Ok();
        }
    }
}
