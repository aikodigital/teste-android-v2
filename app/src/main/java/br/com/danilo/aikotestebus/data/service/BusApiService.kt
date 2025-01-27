package br.com.danilo.aikotestebus.data.service

import br.com.danilo.aikotestebus.data.model.ArrivalForecastResponse
import br.com.danilo.aikotestebus.data.model.BusStopLineResponse
import br.com.danilo.aikotestebus.data.model.BusesPositionResponse
import br.com.danilo.aikotestebus.data.model.LineDetailResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface BusApiService {

    @POST("Login/Autenticar")
    suspend fun authenticator(): Boolean

    @GET("Linha/Buscar")
    suspend fun getBusLine(
        @Query("termosBusca") query: String
    ): List<LineDetailResponse>?

    @GET("Posicao")
    suspend fun getBusesPosition(): BusesPositionResponse?

    @GET("Previsao")
    suspend fun getArrivalForecastTime(
        @Query("codigoParada") idStop: Int,
        @Query("codigoLinha") idLine: Int
    ): ArrivalForecastResponse?

    @GET("Parada/BuscarParadasPorLinha")
    suspend fun getBusStopByLine(
        @Query("codigoLinha") idLine: Int
    ): List<BusStopLineResponse>?

}