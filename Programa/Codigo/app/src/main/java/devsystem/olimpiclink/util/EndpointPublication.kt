package devsystem.olimpiclink.util

import devsystem.olimpiclink.model.PublicationModelGet
import devsystem.olimpiclink.model.PublicationModelPost
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EndpointPublication {
    @GET("/api/publication/feed/{id}")
    suspend fun publicationsGet(@Path("id") id : Int) : List<PublicationModelGet>

    @GET("/api/publication/user/{id}")
    suspend fun publicationsGetID(
        @Path("id") id : Int
    ) : List<PublicationModelGet>
    @GET("/api/publication/user/{id}/galery")
    suspend fun publicationsGetIDGalery(
        @Path("id") id : Int
    ) : List<PublicationModelGet>

    @GET("/api/publication/seguindo/{id}")
    suspend fun publicationsGetSeguindo(
        @Path("id") id : Int
    ) : List<PublicationModelGet>

    @GET("/api/publication/search/{conteudo}")
    suspend fun publicationsGetSearch(
        @Path("conteudo") conteudo : String
    ) : List<PublicationModelGet>

    @GET("/api/publication/comunities/{id}")
    suspend fun publicationsGetComunity(@Path("id") id : Int) : List<PublicationModelGet>

    @POST("api/publication")
    suspend fun publicationsPost(
        @Body new_publication : PublicationModelPost
    ) : Response<Void>
}