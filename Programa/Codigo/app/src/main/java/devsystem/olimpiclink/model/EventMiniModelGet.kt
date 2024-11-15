package devsystem.olimpiclink.model

data class EventMiniModelGet(
    val idEvent : Int,
    val comunity_id : Int,
    val comunity_name : String,
    val comunity_picture : String,
    val nameEvent : String,
    val descriptionEvent : String,
    val url_picture_event : List<String>
)
