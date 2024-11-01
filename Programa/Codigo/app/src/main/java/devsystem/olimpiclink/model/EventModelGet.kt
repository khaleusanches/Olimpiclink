data class EventModelGet(
    val event_id: Int,
    val user_id: Int,
    val title_event: String,
    val description_event: String,

    val cover_image_event: String?, // Imagem de capa
    val images_event: List<String?>, // Lista de imagens do evento para o carrossel

    val date_start_event: String,
    val date_end_event: String,

    val place_id: Int?,
    val name_place: String?,

    val comunity_id: Int?,
    val login_user: String,
    val url_profile_picture_user: String
) {
    fun listarImagens(): MutableList<String> {
        val lista_imagens: MutableList<String> = mutableListOf()
        cover_image_event?.let { lista_imagens.add(it) } //Capa
        lista_imagens.addAll(images_event.filterNotNull()) // Carrossel
        return lista_imagens
    }
}
