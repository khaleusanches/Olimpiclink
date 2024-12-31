package devsystem.olimpiclink.model

data class ComunityModelPost(
    val name_comunity: String,
    val description_comunity: String,
    val category_id: Int,
    val regras_comunity: String,
    val icon_comunity: String,
    val banner_comunity: String,
    val id_user: Int
)
