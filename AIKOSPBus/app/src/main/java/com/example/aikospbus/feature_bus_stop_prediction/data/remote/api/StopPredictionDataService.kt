package com.example.aikospbus.feature_bus_stop_prediction.data.remote.api

import com.example.aikospbus.feature_api_sp_trans.remote.api.SPTransApi
import com.example.aikospbus.feature_bus_stop_prediction.data.remote.dto.BusStopPredictionDto

interface StopPredictionDataService {

    suspend fun requestStopPrediction(cookie: String, stopCode: Int): BusStopPredictionDto?

    companion object {
        fun create(): StopPredictionDataService {
            return StopPredictionDataServiceImpl(
                apiService = SPTransApi.retrofitService
            )
        }
    }
}