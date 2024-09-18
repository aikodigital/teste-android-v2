package br.com.aikosptrans.data.remote.service

import br.com.aikosptrans.data.remote.model.response.ArrivalForecastResponse
import br.com.aikosptrans.data.remote.model.response.BusLineDetailResponse
import br.com.aikosptrans.data.remote.model.response.BusStopResponse
import br.com.aikosptrans.data.remote.model.response.BusesLocationResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("Login/Autenticar")
    suspend fun authenticate(): Boolean
    @GET("Posicao")
    suspend fun getBusesLocation(): BusesLocationResponse
    @GET("Parada/Buscar")
    suspend fun getBusStop(
        @Query("termosBusca") query: String
    ): List<BusStopResponse>
    @GET("Linha/Buscar")
    suspend fun getBusLine(
        @Query("termosBusca") query: String
    ): List<BusLineDetailResponse>
    @GET("Parada/BuscarParadasPorLinha")
    suspend fun getBusStopByLine(
        @Query("codigoLinha") idLine: String
    ): List<BusStopResponse>
    @GET("Previsao")
    suspend fun getArrivalForecastTime(
        @Query("codigoParada") idStop: String,
        @Query("codigoLinha") idLine: String
    ): ArrivalForecastResponse
}