package com.example.aikospbus.feature_bus_stop_prediction.data.data_source

import com.example.aikospbus.feature_bus_stop_prediction.domain.model.StopPredictionModel

interface StopPredictionDataSource {

    suspend fun insertStopPrediction(stopPredictionModel: StopPredictionModel)

    suspend fun getStopPrediction() : List<StopPredictionModel>?
}