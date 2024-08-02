using Microsoft.AspNetCore.Mvc;
using olimpiclink.database.Models;

namespace olimpiclink.database.Controllers
{
    [ApiController]
    [Route("api/v1/category")]
    public class CategoryController : ControllerBase
    {
        private readonly ICategoryRepository _categoryRepository;

        public CategoryController(ICategoryRepository categoryRepository)
        {
            _categoryRepository = categoryRepository;
        }

        [HttpPost]
        public IActionResult Add(CategoryModel category)
        {
            var teste = new Category(category.name_category);
            _categoryRepository.add(teste);
            return Ok();
        }
        [HttpGet]
        public IActionResult Get()
        {
            return  Ok(_categoryRepository.Get());
        }
    }
}
