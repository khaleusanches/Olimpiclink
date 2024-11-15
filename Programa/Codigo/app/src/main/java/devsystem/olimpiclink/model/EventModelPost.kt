package devsystem.olimpiclink.model

data class EventModelPost(
    val user_id: Int,
    val title_event: String,
    val description_event: String,

    val images_event: List<String?>,

    val date_start_event: String,
    val date_end_event: String,

    val place_id: Int?,
    val event_id: Int?
)
