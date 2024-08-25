package com.example.aikospbus.feature_api_sp_trans.remote.api

import com.example.aikospbus.feature_bus_lines.data.remote.dto.BusLinesDto
import com.example.aikospbus.feature_bus_location.data.remote.dto.BusDto
import com.example.aikospbus.feature_bus_corridor.data.remote.dto.BusCorridorDto
import com.example.aikospbus.feature_bus_stops.data.remote.dto.BusStopsDto
import com.example.aikospbus.feature_api_sp_trans.remote.models.PrevisaoChegada
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface SPTransApiService {
    @POST("Login/Autenticar")
    suspend fun authentication(
        @Query("token")
        token: String
    ): Response<Boolean>

    //FEATURE_BUS_LINES
    @GET("Linha/Buscar")
    suspend fun getLine(
        @Header("Cookie")
        cookie: String,
        @Query("termosBusca")
        termosBusca: String
    ): List<BusLinesDto>

    //FEATURE_BUS_LOCATION
    @GET("Posicao/Linha")
    suspend fun getLinePosition(
        @Header("Cookie")
        cookie: String,
        @Query("codigoLinha")
        codigoLinha: Int
    ): BusDto

    //FEATURE_BUS_STOPS
    @GET("Parada/Buscar")
    suspend fun getStops(
        @Header("Cookie") cookie: String,
        @Query("termosBusca") termosBusca: String
    ): List<BusStopsDto>

    //FEATURE_BUS_CORRIDORS
    @GET("Corredor")
    suspend fun getCorredores(
        @Header("Cookie") cookie: String
    ): List<BusCorridorDto>

    @GET("Previsao")
    suspend fun getPrevisaoChegada(
        @Header("Cookie") cookie: String,
        @Query("codigoParada") codigoParada: Int,
        @Query("codigoLinha") codigoLinha: Int
    ): PrevisaoChegada
}

object SPTransApi {
    private const val BASE_URL = "http://api.olhovivo.sptrans.com.br/v2.1/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(SPTransHttpClient.getClient())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: SPTransApiService by lazy {
        retrofit.create(SPTransApiService::class.java)
    }
}


