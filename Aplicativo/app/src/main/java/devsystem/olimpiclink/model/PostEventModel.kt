package devsystem.olimpiclink.model

import java.util.Date

data class PostEventModel(
    val place_id: Int,
    val comunity_id: Int,
    val leader_id: Int,
    val nameEvent: String,
    val descriptionEvent: String,
    val dateTimeEvent: String,
    val closingDateTimeEvent: String,
    val pictures_event: Array<String>?,
    val endereco : String
)

