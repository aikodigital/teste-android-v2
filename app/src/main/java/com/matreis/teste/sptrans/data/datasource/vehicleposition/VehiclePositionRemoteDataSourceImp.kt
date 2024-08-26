package com.matreis.teste.sptrans.data.datasource.vehicleposition

import com.matreis.teste.sptrans.data.api.SpTransService
import com.matreis.teste.sptrans.domain.model.TimeWithVehicle
import com.matreis.teste.sptrans.domain.model.VehiclePosition
import retrofit2.Response
import javax.inject.Inject

class VehiclePositionRemoteDataSourceImp @Inject constructor(
    private val service: SpTransService
): VehiclePositionRemoteDataSource {
    override suspend fun getAllVehiclePosition(): Response<VehiclePosition> = service.getAllVehiclePosition()
    override suspend fun getVehiclePositionByLine(lineCode: Long): Response<TimeWithVehicle> = service.getVehiclePositionByLine(lineCode)
}