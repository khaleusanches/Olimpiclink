package devsystem.olimpiclink.util

import devsystem.olimpiclink.model.ComunityModel
import devsystem.olimpiclink.model.ComunityModelPost
import devsystem.olimpiclink.model.FollowComunityModel
import devsystem.olimpiclink.model.PublicationModelGet
import devsystem.olimpiclink.model.RequestMessages
import devsystem.olimpiclink.model.RequestParticipationComunityModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EndpointComunity {
    @GET("/api/comunities/{id}")
    suspend fun getComunityId(@Path("id") id : Int) : ComunityModel
    @GET("/api/comunities")
    suspend fun getComunity() : List<ComunityModel>

    @GET("/api/comunities/user/{id}")
    suspend fun getComunityCardId(@Path("id") id : Int) : List<ComunityModel>

    @POST("/api/comunities")
    suspend fun postComunity(@Body new_comunity : ComunityModelPost) : Response<Void>

    @GET("/api/comunities/FF/{id_user}&&{id_comunity}")
    suspend fun comunityFF(@Path("id_user") id : Int, @Path("id_comunity") id_comunity : Int) : RequestMessages

    @POST("/api/comunities/follow")
    suspend fun comunityFollow(@Body new_follow : FollowComunityModel) : Response<Void>

    @DELETE("/api/comunities/desFollow/{id_user}&&{id_comunity}")
    suspend fun comunityDesFollow(@Path("id_user") id_user : Int, @Path("id_comunity") id_comunity : Int) : Response<Void>

    @GET("/api/comunities/RP/{id_user}&&{id_comunity}")
    suspend fun getRequestParticipation(@Path("id_user") id : Int, @Path("id_comunity") id_comunity : Int) : RequestMessages

    @POST("/api/comunities/requestParticipation")
    suspend fun postRequestComunity(@Body new_participation : RequestParticipationComunityModel) : Response<Void>
}