package com.matreis.teste.sptrans.data.repository.vehicleposition

import com.matreis.teste.sptrans.data.datasource.vehicleposition.VehiclePositionRemoteDataSource
import com.matreis.teste.sptrans.domain.model.TimeWithVehicle
import com.matreis.teste.sptrans.domain.model.VehiclePosition
import retrofit2.Response
import javax.inject.Inject

class VehiclePositionRepositoryImp @Inject constructor(
    private val vehiclePositionRemoteDataSource: VehiclePositionRemoteDataSource
) : VehiclePositionRepository {
    override suspend fun getAllVehiclePosition(): Response<VehiclePosition> = vehiclePositionRemoteDataSource.getAllVehiclePosition()
    override suspend fun getVehiclePositionByLine(lineCode: Long): Response<TimeWithVehicle> = vehiclePositionRemoteDataSource.getVehiclePositionByLine(lineCode)
}