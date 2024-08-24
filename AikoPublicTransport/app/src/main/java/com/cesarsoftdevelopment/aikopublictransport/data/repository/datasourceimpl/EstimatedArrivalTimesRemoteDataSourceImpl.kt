package com.cesarsoftdevelopment.aikopublictransport.data.repository.datasourceimpl

import com.cesarsoftdevelopment.aikopublictransport.data.model.EstimatedArrivalTime
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.EstimatedArrivalTimesRemoteDataSource
import retrofit2.Response

class EstimatedArrivalTimesRemoteDataSourceImpl : EstimatedArrivalTimesRemoteDataSource {
    override suspend fun getEstimatedArrivalTimesByStop(stopCode: Int): Response<EstimatedArrivalTime> {
        TODO("Not yet implemented")
    }


}