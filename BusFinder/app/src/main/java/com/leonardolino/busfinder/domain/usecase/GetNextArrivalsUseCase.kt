package com.leonardolino.busfinder.domain.usecase

import com.leonardolino.busfinder.domain.model.NextArrivals
import com.leonardolino.busfinder.domain.repository.BusRepository

class GetNextArrivalsUseCase(private val repository: BusRepository) {
    suspend operator fun invoke(stopCode: Int): NextArrivals {
        return repository.getNextArrivals(stopCode)
    }
}