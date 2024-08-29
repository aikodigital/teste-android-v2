package br.com.aiko.projetoolhovivo.data.service.forecast

import android.util.Log
import br.com.aiko.projetoolhovivo.data.model.forecast.ForecastByLine
import br.com.aiko.projetoolhovivo.data.model.forecast.ForecastByStop
import retrofit2.Retrofit
import javax.inject.Inject

class ForecastRepository @Inject constructor(
    private val retrofit: Retrofit
) {
    suspend fun getForecastByStopCode(token: String, codeStop: Int): Result<ForecastByStop> {
        return try {
            val forecastService: ForecastService = retrofit.create(ForecastService::class.java)
            val getForecast = forecastService.getForecastByStopCode(token, codeStop)
            Result.success(getForecast)
        } catch (e: Exception) {
            Log.e("RETROFIT", e.message.toString())
            Log.e("RETROFIT", e.stackTrace.toString())
            Result.failure(e)
        }
    }
    suspend fun getForecastByLineCode(token: String, codeLine: Int): Result<ForecastByLine> {
        return try {
            val forecastService: ForecastService = retrofit.create(ForecastService::class.java)
            val getForecast = forecastService.getForecastByLineCode(token, codeLine)
            Result.success(getForecast)
        } catch (e: Exception) {
            Log.e("RETROFIT", e.message.toString())
            Log.e("RETROFIT", e.stackTrace.toString())
            Result.failure(e)
        }
    }
}