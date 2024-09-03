package devsystem.olimpiclink.model.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiCliente {
    companion object {
        val URL = "https://3b06-189-29-146-118.ngrok-free.app/"

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}