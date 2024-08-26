package com.leonardolino.busfinder.domain.usecase

import com.leonardolino.busfinder.domain.model.BusStop
import com.leonardolino.busfinder.domain.repository.BusRepository

class GetBusStopsUseCase(private val repository: BusRepository) {
    suspend operator fun invoke(terms: String): List<BusStop> {
        return repository.getBusStops(terms)
    }
}