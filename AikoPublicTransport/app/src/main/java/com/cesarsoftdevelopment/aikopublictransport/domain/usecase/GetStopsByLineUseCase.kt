package com.cesarsoftdevelopment.aikopublictransport.domain.usecase

import com.cesarsoftdevelopment.aikopublictransport.domain.repository.StopRepository

class GetStopByLineUseCase(private val stopsRepository: StopRepository) {
    suspend fun invoke(lineCode: Int) = stopsRepository



}