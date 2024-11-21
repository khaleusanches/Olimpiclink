namespace olimpiclink.database.Models.Comunities
{
    public record AddComunityModel
        (
            string name_comunity,
            string description_comunity,
            int category_id,
            string regras_comunity,
            string icon_comunity
        )
    { }
}
