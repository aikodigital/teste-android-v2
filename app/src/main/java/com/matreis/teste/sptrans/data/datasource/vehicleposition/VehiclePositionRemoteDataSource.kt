package com.matreis.teste.sptrans.data.datasource.vehicleposition

import com.matreis.teste.sptrans.domain.model.TimeWithVehicle
import com.matreis.teste.sptrans.domain.model.VehiclePosition
import retrofit2.Response

interface VehiclePositionRemoteDataSource {
    suspend fun getAllVehiclePosition(): Response<VehiclePosition>
    suspend fun getVehiclePositionByLine(lineCode: Long): Response<TimeWithVehicle>

}