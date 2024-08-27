package com.cesarsoftdevelopment.aikopublictransport.domain.usecase

import com.cesarsoftdevelopment.aikopublictransport.domain.repository.BusLinesRepository

class GetBusLinesUseCase(private val busLinesRepository: BusLinesRepository) {
    suspend operator fun invoke(termsSearch : String) = busLinesRepository.getBusLines(termsSearch)

}