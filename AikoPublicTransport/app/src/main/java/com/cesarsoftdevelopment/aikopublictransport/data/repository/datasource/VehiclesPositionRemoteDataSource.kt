package com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource

import com.cesarsoftdevelopment.aikopublictransport.data.model.VehiclePosition
import retrofit2.Response

interface VehiclesPositionRemoteDataSource {
    suspend fun getVehiclesPositionByLine(lineCode: Int): Response<VehiclePosition>
}