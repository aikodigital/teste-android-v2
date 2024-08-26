package com.example.aikospbus.feature_bus_stop_prediction.data.data_source

import com.example.aikospbus.feature_bus_stop_prediction.domain.model.StopPredictionModel

class StopPredictionLocalDataSource(private val stopPredictionDao: StopPredictionDao) :
    StopPredictionDataSource {

    override suspend fun insertStopPrediction(stopPredictionModel: StopPredictionModel) {
        return stopPredictionDao.insertStopPrediction(stopPredictionModel)
    }

    override suspend fun getStopPrediction(): List<StopPredictionModel>? {
        return stopPredictionDao.getStopPrediction()
    }
}