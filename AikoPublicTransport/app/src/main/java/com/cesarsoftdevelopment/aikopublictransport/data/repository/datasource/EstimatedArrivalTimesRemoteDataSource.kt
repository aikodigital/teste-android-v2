package com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource

import com.cesarsoftdevelopment.aikopublictransport.data.model.EstimatedArrivalTime
import retrofit2.Response

interface EstimatedArrivalTimesRemoteDataSource {
    suspend fun getEstimatedArrivalTimesByStop(stopCode: Long?): Response<EstimatedArrivalTime>


}