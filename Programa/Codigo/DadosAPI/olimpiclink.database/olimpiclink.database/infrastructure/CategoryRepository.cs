using Microsoft.AspNetCore.Connections;
using olimpiclink.database.Models;

namespace olimpiclink.database.infrastructure
{
    public class CategoryRepository : ICategoryRepository
    {
        private readonly Connection _context = new Connection();
        public void add(Category category)
        {
            _context.Categories.Add(category);
            _context.SaveChanges();
        }

        public List<Category> Get()
        {
            return _context.Categories.ToList();
        }
    }
}
