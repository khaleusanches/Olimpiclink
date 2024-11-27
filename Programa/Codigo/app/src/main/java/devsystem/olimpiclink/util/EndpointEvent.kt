package devsystem.olimpiclink.util

import devsystem.olimpiclink.model.EventMiniModelGet
import devsystem.olimpiclink.model.EventModelGet
import retrofit2.http.GET
import retrofit2.http.Path

interface EndpointEvent {
    @GET("/api/Event/{id}")
    suspend fun eventGet(@Path("id") id : Int) : EventModelGet

    @GET("/api/Event/mini")
    suspend fun eventMiniGet() : List<EventMiniModelGet>

    @GET("/api/mini/comunity/{id}")
    suspend fun eventMiniGetComunity(
        @Path("id") id : Int
    ) : List<EventMiniModelGet>

    @GET("/api/Event/mini/{id}")
    suspend fun eventMiniGetSeguindo(
        @Path("id") id : Int
    ) : List<EventMiniModelGet>

    @GET("/api/Event/comunity/{id}")
    suspend fun eventGetComunity(
        @Path("id") id : Int
    ) : List<EventMiniModelGet>
}