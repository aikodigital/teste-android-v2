package com.example.aikospbus.feature_bus_location.data.remote.api

import com.example.aikospbus.feature_api_sp_trans.remote.api.SPTransApiService
import com.example.aikospbus.feature_api_sp_trans.remote.api.SPTransHttpClient
import com.example.aikospbus.feature_bus_location.data.remote.dto.Bus
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BusLocationService {

    @GET("Posicao/Linha")
    suspend fun getLinePosition(
        @Header("Cookie")
        cookie: String,
        @Query("codigoLinha")
        codigoLinha: Int
    ): Bus

    object SPTransApi {
        private const val BASE_URL = "http://api.olhovivo.sptrans.com.br/v2.1/"

        private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(SPTransHttpClient.getClient())
            .baseUrl(BASE_URL)
            .build()

        val retrofitService: BusLocationService by lazy {
            retrofit.create(BusLocationService::class.java)
        }
    }
}