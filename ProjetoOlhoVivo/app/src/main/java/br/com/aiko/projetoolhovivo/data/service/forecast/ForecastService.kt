package br.com.aiko.projetoolhovivo.data.service.forecast

import br.com.aiko.projetoolhovivo.data.model.forecast.ForecastByLine
import br.com.aiko.projetoolhovivo.data.model.forecast.ForecastByStop
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastService {
    @GET("Previsao/Parada")
    suspend fun getForecastByStopCode(
        @Query("token") token: String,
        @Query("codigoParada") codeStop: Int
    ): ForecastByStop
    @GET("Previsao/Linha")
    suspend fun getForecastByLineCode(
        @Query("token") token: String,
        @Query("codigoLinha") codeLine: Int
    ): ForecastByLine
}