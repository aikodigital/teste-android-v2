package com.cesarsoftdevelopment.aikopublictransport.data.repository

import com.cesarsoftdevelopment.aikopublictransport.data.model.VehiclePosition
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.VehiclesPositionRemoteDataSource
import com.cesarsoftdevelopment.aikopublictransport.domain.repository.VehiclesPositionRepository
import com.cesarsoftdevelopment.aikopublictransport.utils.Resource

class VehiclesPositionRepositoryImpl(
    private val vehiclesPositionRemoteDataSource: VehiclesPositionRemoteDataSource
) : VehiclesPositionRepository {
    override suspend fun getVehiclesPositionByLine(lineCode: Int): Resource<VehiclePosition> {
        TODO("Not yet implemented")
    }
}