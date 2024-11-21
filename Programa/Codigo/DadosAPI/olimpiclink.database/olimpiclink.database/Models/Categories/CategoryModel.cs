using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace olimpiclink.database.Models.Categories
{
    [Table("categories")]
    public class CategoryModel
    {
        [Key]
        public int id_category { get; set; }
        public string name_category { get; set; }
        public string url_icon_category { get; set; }

        public CategoryModel(string name_category, string url_icon_category)
        {
            this.name_category = name_category;
            this.url_icon_category = url_icon_category;
        }
    }
}
