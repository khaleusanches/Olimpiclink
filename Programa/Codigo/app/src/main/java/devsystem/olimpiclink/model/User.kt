package devsystem.olimpiclink.model

data class User(
    val id_user : Int,
    val name_user : String,
    val activate_user : Boolean,
    val email_user : String,
    val password_user : String,
    val login_user : String,
    val profile_picture_user : String?,
    val created_at_user : String,
    val updated_at_user : String
)
