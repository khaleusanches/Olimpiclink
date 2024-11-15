package devsystem.olimpiclink.util

import devsystem.olimpiclink.model.EventMiniModelGet
import retrofit2.http.GET
import retrofit2.http.Path

interface EndpointEvent {
    @GET("/api/Event/mini")
    suspend fun eventMiniGet() : List<EventMiniModelGet>
    @GET("api/Event/mini/{id}")
    suspend fun eventMiniGetSeguindo(
        @Path("id") id : Int
    ) : List<EventMiniModelGet>
}