package com.example.aikospbus.feature_bus_stop_prediction.data.remote.api

import com.example.aikospbus.feature_api_sp_trans.remote.api.SPTransApiService
import com.example.aikospbus.feature_bus_stop_prediction.data.remote.dto.BusStopPredictionDto
import retrofit2.HttpException

class StopPredictionDataServiceImpl(private val apiService: SPTransApiService) :
    StopPredictionDataService {

    override suspend fun requestStopPrediction(
        cookie: String,
        stopCode: Int,
    ): BusStopPredictionDto? {
        return try {
            val response = apiService.getPrevisaoChegada(cookie, stopCode)
            response
        } catch (e: HttpException) {
            null
        } catch (e: Exception) {
            null
        }
    }
}