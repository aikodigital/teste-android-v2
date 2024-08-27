package com.cesarsoftdevelopment.aikopublictransport.domain.usecase

import com.cesarsoftdevelopment.aikopublictransport.domain.repository.EstimatedArrivalTimesRepository

class GetEstimatedArrivalTimesByStopUseCase(
    private val estimatedArrivalTimesRepository: EstimatedArrivalTimesRepository
) {
    suspend operator fun invoke(stopCode : Long?) = estimatedArrivalTimesRepository.getEstimatedArrivalTimesByStop(stopCode)


}