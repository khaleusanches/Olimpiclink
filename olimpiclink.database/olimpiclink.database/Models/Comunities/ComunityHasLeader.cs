using System.ComponentModel.DataAnnotations;

namespace olimpiclink.database.Models.Comunities
{
    public class ComunityHasLeader
    {
        [Key]
        public int comunity_id { get; set; }
        public int leader_id { get; set; }

        public ComunityHasLeader(int comunity_id, int leader_id)
        {
            this.comunity_id = comunity_id;
            this.leader_id = leader_id;
        }
    }
}
