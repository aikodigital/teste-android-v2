package com.example.aikospbus.feature_bus_stop_prediction.domain.use_case

import com.example.aikospbus.feature_bus_stop_prediction.data.repository.StopPredictionRepository
import com.example.aikospbus.feature_bus_stop_prediction.domain.model.StopPredictionModel
import javax.inject.Inject

class InsertStopPredictionUseCase @Inject constructor(private val stopPredictionRepository: StopPredictionRepository) {

    suspend operator fun invoke(stopPredictionModel: StopPredictionModel) {
        stopPredictionRepository.insertStopPrediction(stopPredictionModel)
    }
}