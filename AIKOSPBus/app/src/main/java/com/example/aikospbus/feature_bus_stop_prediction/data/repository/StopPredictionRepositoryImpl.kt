package com.example.aikospbus.feature_bus_stop_prediction.data.repository

import com.example.aikospbus.feature_bus_stop_prediction.data.data_source.StopPredictionDataSource
import com.example.aikospbus.feature_bus_stop_prediction.data.remote.api.StopPredictionDataService
import com.example.aikospbus.feature_bus_stop_prediction.domain.model.StopPredictionModel
import com.example.aikospbus.util.Resource
import io.ktor.client.features.ClientRequestException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StopPredictionRepositoryImpl @Inject constructor(
    private val localDataSource: StopPredictionDataSource,
    private val apiData: StopPredictionDataService
) : StopPredictionRepository {

    override suspend fun insertStopPrediction(stopPredictionModel: StopPredictionModel) {
        localDataSource.insertStopPrediction(stopPredictionModel)
    }

    override suspend fun getStopPrediction() = localDataSource.getStopPrediction()

    override fun getRemoteStopPrediction(
        cookie: String,
        stopCode: Int,
    ): Flow<Resource<List<StopPredictionModel>?>> = flow {

        emit(Resource.Loading())
        val localStopPredictionData = localDataSource.getStopPrediction()
        emit(Resource.Loading(data = localStopPredictionData))

        try {
            val busStopPredictionData = apiData.requestStopPrediction(cookie, stopCode)

            if (busStopPredictionData == null) {
                emit(
                    Resource.Error(
                        message = "oops, something went wrong",
                        data = localStopPredictionData
                    )
                )
            } else {
                val updateStopModelData = StopPredictionModel(
                    horaPrevisao = busStopPredictionData.horaPrevisao,
                    parada = busStopPredictionData.parada
                )

                localDataSource.insertStopPrediction(updateStopModelData)
                val newBusLocationData = localDataSource.getStopPrediction()
                emit(Resource.Success(data = newBusLocationData))
            }
        } catch (e: ClientRequestException) {
            emit(
                Resource.Error(
                    message = "oops, something went wrong",
                    data = localStopPredictionData
                )
            )
        }
    }
}