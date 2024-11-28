package devsystem.olimpiclink.model

data class EventModelGet (
    val idEvent: Int,
    val place_id: Int,
    val place_name: String,
    val comunity_id: Int,
    val comunity_name: String,
    val comunity_picture: String,
    val nameEvent: String,
    val descriptionEvent: String,
    val dateTimeEvent: String,
    val closingDateTimeEvent: String,
    val url_picture_event: List<String>,
    val marked_presences: Int,
    val endereco : String
)