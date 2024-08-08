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
        ConnectionContext context = new ConnectionContext();
        [HttpGet("add/{name_category}")] //Define que o metodo a seguir é GET, ou seja, ele envia dados, não recebe.
        public async Task<IActionResult> Add(string name_category, CancellationToken ct)
        {

             //Chama o "contexto", a conexão com o banco de dados.

            var new_category = new CategoryModel(name_category); //objeto category, porém feito apenas com o nome, sem ID, pois não inserimos ID.

            if(await context.Categories.AnyAsync(category => category.name_category == name_category, ct))
            {
                return Ok("vacilo");
            }

            await context.Categories.AddAsync(new_category, ct);
            await context.SaveChangesAsync(ct);
            return Ok();
        }

        [HttpGet("{id_category}")] // Define que o metodo a seguir é GET, ou seja, ele recebe dados, não envia.
        public async Task<IActionResult> GetID(long? id_category, CancellationToken ct)
        {
             return Ok(await context.Categories.Where(category => category.id_category == id_category).ToListAsync());
        }
        [HttpGet] // Define que o metodo a seguir é GET, ou seja, ele recebe dados, não envia.
        public async Task<IActionResult> GetAll(long? id_category, CancellationToken ct)
        {
            var get_categories = await context.Categories.ToListAsync(ct);
            return Ok(get_categories);
        }

        [HttpGet("alter/{id_category}/{name_category}")]
        public async Task<IActionResult> Put(long? id_category, string name_category, CancellationToken ct)
        {
            var category = await context.Categories.SingleOrDefaultAsync(category => category.id_category == id_category, ct);
            if (category == null)
            {
                return Ok("Categoria não encontrada");
            }
            category.name_category = name_category;
            await context.SaveChangesAsync(ct);
            return Ok();
        }

        [HttpGet("delete/{id_category}")]
        public async Task<IActionResult> Delete(long? id_category, CancellationToken ct)
        {
            var category = await context.Categories.SingleOrDefaultAsync(category => category.id_category == id_category, ct);
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
