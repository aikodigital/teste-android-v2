package com.cesarsoftdevelopment.aikopublictransport.domain.usecase

import com.cesarsoftdevelopment.aikopublictransport.domain.repository.BusLineRepository

class GetBusLineUseCase(private val busLineRepository: BusLineRepository) {
    suspend fun invoke(termsSearch : String) = busLineRepository



}