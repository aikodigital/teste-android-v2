package com.cesarsoftdevelopment.aikopublictransport.domain.repository

import com.cesarsoftdevelopment.aikopublictransport.data.model.EstimatedArrivalTime
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.EstimatedArrivalTimesRemoteDataSource
import com.cesarsoftdevelopment.aikopublictransport.utils.Resource
interface EstimatedArrivalTimesRepository {
    suspend fun getEstimatedArrivalTimesByStop(stopCode : Long?) : Resource<EstimatedArrivalTime>
}