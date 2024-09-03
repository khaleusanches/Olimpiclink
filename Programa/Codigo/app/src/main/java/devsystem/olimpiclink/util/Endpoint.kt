package devsystem.olimpiclink.model.util

import devsystem.olimpiclink.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoint {
    @GET("api/users/login/{login_user}&{password_user}")
    fun userLogin(
        @Path("login_user") login_user : String,
        @Path("password_user") password_user : String
    ) : Call<User>
}