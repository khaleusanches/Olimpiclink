using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using olimpiclink.database.Data;
using olimpiclink.database.Models;
using System.Linq;

namespace olimpiclink.database.Controllers
{
    [ApiController] 
    [Route("api/v1/category")]  //define a rota da url
    public class CategoryController : Controller
    {
        [HttpPost] //Define que o metodo a seguir é POST, ou seja, ele envia dados, não recebe.
        public async Task<IActionResult> Add(AddCategoryModel request)
        {
            ConnectionContext context = new ConnectionContext(); //Chama o "contexto", a conexão com o banco de dados.

            var new_category = new CategoryModel(request.name_category); //objeto category, porém feito apenas com o nome, sem ID, pois não inserimos ID.

            if(await context.Categories.AnyAsync(category => category.name_category == request.name_category))
            {
                return Ok("vacilo");
            }

            await context.Categories.AddAsync(new_category);
            await context.SaveChangesAsync();
            return Ok();
        }

        [HttpGet] // Define que o metodo a seguir é GET, ou seja, ele recebe dados, não envia.
        public async Task<IActionResult> Get()
        {
            ConnectionContext context = new ConnectionContext();
            // Get com filtro
            //var get_category = await context.Categories.Where(category => category.name_category == "teste").ToListAsync(); 
            // Get all
            var get_categories = await context.Categories.ToListAsync();
            return Ok(get_categories);
        }
    }
}
