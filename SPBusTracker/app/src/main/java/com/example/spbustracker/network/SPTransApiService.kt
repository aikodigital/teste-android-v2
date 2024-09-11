package com.example.spbustracker.network

import android.content.Context
import com.example.spbustracker.ServiceURL
import com.example.spbustracker.model.VehiclePositionResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SPTransApiService {

    @POST(ServiceURL.AUTENTICAR_API)
    suspend fun autenticar(@Query("token") token: String): retrofit2.Response<Unit>

    @GET(ServiceURL.POSITIONS_BUS)
    suspend fun getVehiclePositions(): retrofit2.Response<VehiclePositionResponse>

    companion object {
        fun create(
            token: String,
            context: Context,
            addInterceptor: Boolean = true
        ): SPTransApiService {
            val client = OkHttpClient.Builder()
            val authInterceptor = AuthInterceptor(token)

            if (addInterceptor) {
                client
                    .cookieJar(CookieManager(context))
                    .addInterceptor(authInterceptor)
            } else {
                client
                    .cookieJar(CookieManager(context))
            }

            return Retrofit.Builder()
                .baseUrl(ServiceURL.URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SPTransApiService::class.java)
        }
    }

}
