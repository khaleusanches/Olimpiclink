package devsystem.olimpiclink.model

data class RequestParticipationComunityModel(
    val user_id: Int,
    val comunity_id: Int,
    val analisado: Boolean = false,
    val acepted: Boolean? = null
)
