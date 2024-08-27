package com.cesarsoftdevelopment.aikopublictransport.data.repository

import com.cesarsoftdevelopment.aikopublictransport.data.model.EstimatedArrivalTime
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.EstimatedArrivalTimesRemoteDataSource
import com.cesarsoftdevelopment.aikopublictransport.domain.repository.EstimatedArrivalTimesRepository
import com.cesarsoftdevelopment.aikopublictransport.utils.Resource

class EstimatedArrivalTimesRepositoryImpl(
    private val estimatedArrivalTimesRemoteDataSource: EstimatedArrivalTimesRemoteDataSource
) : EstimatedArrivalTimesRepository {

    override suspend fun getEstimatedArrivalTimesByStop(stopCode: Long?): Resource<EstimatedArrivalTime> {
        val response = estimatedArrivalTimesRemoteDataSource.getEstimatedArrivalTimesByStop(stopCode)

        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

}