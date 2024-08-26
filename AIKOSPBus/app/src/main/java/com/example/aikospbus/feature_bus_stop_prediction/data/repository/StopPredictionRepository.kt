package com.example.aikospbus.feature_bus_stop_prediction.data.repository

import com.example.aikospbus.feature_bus_stop_prediction.domain.model.StopPredictionModel
import com.example.aikospbus.util.Resource
import kotlinx.coroutines.flow.Flow

interface StopPredictionRepository {

    suspend fun insertStopPrediction(stopPredictionModel: StopPredictionModel)

    suspend fun getStopPrediction(): List<StopPredictionModel>?

    fun getRemoteStopPrediction(
        cookie: String,
        stopCode: Int
    ): Flow<Resource<List<StopPredictionModel>?>>
}