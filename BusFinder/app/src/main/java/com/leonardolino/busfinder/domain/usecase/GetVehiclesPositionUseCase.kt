package com.leonardolino.busfinder.domain.usecase

import com.leonardolino.busfinder.domain.model.VehiclePosition
import com.leonardolino.busfinder.domain.repository.BusRepository

class GetVehiclesPositionUseCase(private val repository: BusRepository) {
    suspend operator fun invoke(lineCode: Int): VehiclePosition {
        return repository.getVehiclesPosition(lineCode)
    }
}