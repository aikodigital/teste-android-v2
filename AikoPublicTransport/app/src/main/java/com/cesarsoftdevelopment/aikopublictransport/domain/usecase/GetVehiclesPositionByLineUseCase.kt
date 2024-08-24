package com.cesarsoftdevelopment.aikopublictransport.domain.usecase

import com.cesarsoftdevelopment.aikopublictransport.domain.repository.VehiclesPositionRepository

class GetVehiclesPositionByLineUseCase(private val vehiclesPositionRepository: VehiclesPositionRepository) {
    suspend operator fun invoke(lineCode : Int) = vehiclesPositionRepository.getVehiclesPositionByLine(lineCode)
}