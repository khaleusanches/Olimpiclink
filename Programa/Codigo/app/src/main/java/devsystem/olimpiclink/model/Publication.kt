package devsystem.olimpiclink.model

class Publication(
    val id_publication : Int,
    val user_id : Int,
    val login_user : String,
    val profile_picture_user : String?,
    val text_publication : String,
    val images : ImagesPublicationModel,
    val date_publication : String,
    val comunity_id : Int?,
    val place_id : Int?,
    val event_id : Int?
) {
}