namespace olimpiclink.database.Models
{
    public interface ICategoryRepository
    {
        void add(Category category);
        List<Category> Get();
    }
}
