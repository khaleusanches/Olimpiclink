package devsystem.olimpiclink.model.util

import devsystem.olimpiclink.model.FriendsFollowsFollowersModel
import devsystem.olimpiclink.model.FriendsFollowsFollowersScreenModel
import devsystem.olimpiclink.model.RequestMessages
import devsystem.olimpiclink.model.User
import devsystem.olimpiclink.model.UserFollowModel
import devsystem.olimpiclink.model.UserModelPost
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EndpointUser {
    @POST("api/users")
    suspend fun postUser
                (@Body new_ser : UserModelPost) : Response<Void>

    @GET("api/users/login/{login_user}&{password_user}")
    fun userLogin(
        @Path("login_user") login_user : String,
        @Path("password_user") password_user : String
    ) : Call<User>

    @GET("api/users/check/{login_user}&{email_user}")
    fun emailUsernameCheck(
        @Path("login_user") login_user: String,
        @Path("email_user") email_user: String,
    ) : Call<RequestMessages>

    @GET("api/users/ide/{id}")
    suspend fun getUserID
                (@Path("id") id : Int) : User

    @GET("api/FFF/{id}")
    suspend fun getFriendsFollowsFollowers
                (@Path("id") id : Int) : FriendsFollowsFollowersModel

    @GET("api/followers/{id}")
    suspend fun getFollowers
                (@Path("id") id : Int) : MutableList<FriendsFollowsFollowersScreenModel>

    @GET("/api/followers/{id}&&{login}")
    suspend fun getFollowersName
                (@Path("id") id: Int,
                 @Path("login") login: String) : MutableList<FriendsFollowsFollowersScreenModel>

    @GET("/api/users/search/{name}")
    suspend fun getSearchName
                (@Path("name") name: String) : MutableList<FriendsFollowsFollowersScreenModel>

    @DELETE("api/followers/{id_user}&&{id}")
    suspend fun deleteFollower
                (@Path("id_user") id_user : Int,
                 @Path("id") id : Int)

    @GET("api/follows/{id}")
    suspend fun getFollows
                (@Path("id") id : Int) : MutableList<FriendsFollowsFollowersScreenModel>

    @GET("api/follows/{id}&&{login}")
    suspend fun getFollowsName
                (@Path("id") id: Int,
                 @Path("login") login: String) : MutableList<FriendsFollowsFollowersScreenModel>

    @DELETE("api/follows/{id_user}&&{id}")
    suspend fun deleteFollow
                (@Path("id_user") id_user: Int,
                 @Path("id") id: Int)

    @GET("api/friends/{id}")
    suspend fun getFriends
                (@Path("id") id : Int) : MutableList<FriendsFollowsFollowersScreenModel>

    @GET("api/friends/{id}&&{login}")
    suspend fun getFriendsName
                (@Path("id") id: Int,
                 @Path("login") login: String) : MutableList<FriendsFollowsFollowersScreenModel>

    @DELETE("api/friends/{id_user}&&{id}")
    suspend fun deleteFriend
                (@Path("id_user") id_user: Int,
                 @Path("id") id: Int)

    @GET("api/FFF/{id_user}&&{id}")
    suspend fun getFollowInterrogation
                (@Path("id_user") id_user: Int,
                 @Path("id") id: Int) : RequestMessages

    @POST("api/follows/seguir")
    suspend fun seguirUser
                (@Body new_user_follow : UserFollowModel) : Response<Void>

    @GET("/api/users/userCategories/{id}")
    suspend fun getCategoriesUser(@Path("id") id : Int) : List<String>
}