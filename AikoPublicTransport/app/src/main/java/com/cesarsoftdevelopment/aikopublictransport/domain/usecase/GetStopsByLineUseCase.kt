package com.cesarsoftdevelopment.aikopublictransport.domain.usecase

import com.cesarsoftdevelopment.aikopublictransport.domain.repository.StopsRepository

class GetStopsByLineUseCase(private val stopsRepository: StopsRepository) {
    suspend operator fun invoke(lineCode: Int) = stopsRepository.getStopsByLine(lineCode)



}