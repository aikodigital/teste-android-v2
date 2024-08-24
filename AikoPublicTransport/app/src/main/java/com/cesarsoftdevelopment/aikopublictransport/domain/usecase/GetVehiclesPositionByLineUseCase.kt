package com.cesarsoftdevelopment.aikopublictransport.domain.usecase

import com.cesarsoftdevelopment.aikopublictransport.domain.repository.VehiclePositionRepository

class GetVehiclePositionByLineUseCase(private val vehiclePositionRepository: VehiclePositionRepository) {
    suspend fun invoke() = vehiclePositionRepository




}