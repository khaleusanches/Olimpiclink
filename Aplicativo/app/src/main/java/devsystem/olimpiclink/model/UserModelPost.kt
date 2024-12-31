package devsystem.olimpiclink.model

data class UserModelPost(
    val name_user: String,
    val email_user: String,
    val password_user: String,
    val login_user: String,
    val city_name: String,
    val categories: List<String>
)
