package devsystem.olimpiclink.util

import devsystem.olimpiclink.model.Publication
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET

interface EndpointPublication {
    @GET("/api/publication")
    suspend fun publicationsGet() : List<Publication>
}