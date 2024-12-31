namespace olimpiclink.database.Models.Events
{
    public record PostEventModel
        (
            int place_id,
            int comunity_id,
            int leader_id,
            string nameEvent,
            string descriptionEvent,
            string dateTimeEvent,
            string closingDateTimeEvent,
            string[]? pictures_event,
            string endereco
        )
    {}
}
