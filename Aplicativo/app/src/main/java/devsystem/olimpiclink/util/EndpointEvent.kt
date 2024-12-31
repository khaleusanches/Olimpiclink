package devsystem.olimpiclink.util

import devsystem.olimpiclink.model.EventMiniModelGet
import devsystem.olimpiclink.model.EventModelGet
import devsystem.olimpiclink.model.PostEventModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
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

    @GET("/api/Event/search/{name}")
    suspend fun eventMiniGetSearch(
        @Path("name") name : String
    ) : List<EventMiniModelGet>

    @GET("/api/Event/comunity/{id}")
    suspend fun eventGetComunity(
        @Path("id") id : Int
    ) : List<EventMiniModelGet>

    @GET("/api/Event/comunity/{id}/{month}")
    suspend fun eventGetComunityCalendar(
        @Path("id") id : Int,
        @Path("month") month : Int
    ) : List<EventModelGet>

    @GET("/api/Event/mini/comunity/{id}/data/{day}/{month}")
    suspend fun eventMiniGetData(
        @Path("id") id : Int,
        @Path("day") day : Int,
        @Path("month") month : Int
    ): List<EventMiniModelGet>

    @POST("/api/Event")
    suspend fun postEvent(@Body new_event : PostEventModel) : Response<Void>
}