using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace olimpiclink.database.Models
{
    [Table("categories")]
    public class Category
    {
        [Key]
        public int id_category { get; set; }
        public string name_category { get; set; }

        public Category(string name_category)
        {
            this.name_category = name_category ?? throw new ArgumentNullException(nameof(name_category));
        }
    }
}
