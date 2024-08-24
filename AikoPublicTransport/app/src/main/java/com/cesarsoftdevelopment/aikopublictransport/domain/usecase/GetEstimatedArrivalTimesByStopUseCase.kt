package com.cesarsoftdevelopment.aikopublictransport.domain.usecase

import com.cesarsoftdevelopment.aikopublictransport.domain.repository.EstimatedArrivalTimeRepository

class GetEstimatedArrivalTimeByStopUseCase(
    private val estimatedArrivalTimeRepository: EstimatedArrivalTimeRepository
) {
    suspend fun invoke(stopCode : Int) = estimatedArrivalTimeRepository


}