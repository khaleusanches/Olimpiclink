package devsystem.olimpiclink.model

data class PublicationModelPost(
    val user_id : Int,
    val text_publication : String,

    val image_one_publication : Int?,
    val image_two_publication : Int?,
    val image_three_publication : Int?,
    val image_four_publication : Int?,

    val comunity_id : Int?,
    val place_id : Int?,
    val event_id : Int?,
)
