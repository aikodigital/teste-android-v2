package com.example.aikospbus.feature_bus_stop_prediction.domain.use_case

import com.example.aikospbus.feature_bus_stop_prediction.data.repository.StopPredictionRepository
import com.example.aikospbus.feature_bus_stop_prediction.domain.model.StopPredictionModel
import com.example.aikospbus.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRemoteStopPredictionUseCase @Inject constructor(private val stopPredictionRepository: StopPredictionRepository) {

    operator fun invoke(cookie: String, stopCode: Int): Flow<Resource<List<StopPredictionModel>?>> {
        return stopPredictionRepository.getRemoteStopPrediction(cookie, stopCode)
    }
}