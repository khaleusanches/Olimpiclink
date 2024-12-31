package devsystem.olimpiclink.model

data class ComunityModel(
    val id_comunity: Int,
    val name_comunity: String,
    val description_comunity: String,
    val category_icon: String,
    val url_icon_comunity: String?,
    val url_banner_comunity: String?,
    val regras_comunity: String?,
    val n_seguidores: Int,
    val n_participantes: Int
)
