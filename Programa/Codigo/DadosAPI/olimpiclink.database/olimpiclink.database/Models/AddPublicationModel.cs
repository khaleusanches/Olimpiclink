namespace olimpiclink.database.Models
{
    public record AddPublicationModel
        (
        int user_id,
        string text_publication,
        byte[]? image_one_publication,
        byte[]? image_two_publication,
        byte[]? image_three_publication,
        byte[]? image_four_publication,
        int? comunity_id,
        int? place_id,
        int? event_id
        ){}
}
