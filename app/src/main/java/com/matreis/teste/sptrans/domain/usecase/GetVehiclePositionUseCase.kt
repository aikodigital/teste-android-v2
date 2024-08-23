package com.matreis.teste.sptrans.domain.usecase

import com.matreis.teste.sptrans.data.repository.vehicleposition.VehiclePositionRepository
import javax.inject.Inject

class GetVehiclePositionUseCase @Inject constructor(
    private val vehiclePositionRepository: VehiclePositionRepository
) {
    suspend operator fun invoke(lineCode: Long) = vehiclePositionRepository.getVehiclePositionByLine(lineCode)
    suspend operator fun invoke() = vehiclePositionRepository.getAllVehiclePosition()
}