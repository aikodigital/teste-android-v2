package com.cesarsoftdevelopment.aikopublictransport.data.repository

import com.cesarsoftdevelopment.aikopublictransport.data.model.VehiclePosition
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.VehiclesPositionRemoteDataSource
import com.cesarsoftdevelopment.aikopublictransport.domain.repository.VehiclesPositionRepository
import com.cesarsoftdevelopment.aikopublictransport.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class VehiclesPositionRepositoryImpl(
    private val vehiclesPositionRemoteDataSource: VehiclesPositionRemoteDataSource
) : VehiclesPositionRepository {
    override suspend fun getVehiclesPositionByLine(lineCode: Int): Resource<VehiclePosition?> = coroutineScope {
            val vehiclePosition = async(Dispatchers.IO) {
                vehiclesPositionRemoteDataSource.getVehiclesPositionByLine(lineCode)
            }
            val vehiclePositionResponse = vehiclePosition.await()

            val vehiclePositionResult = if (vehiclePositionResponse.isSuccessful) {
                Resource.Success(vehiclePositionResponse.body())
            } else {
                Resource.Error("Error fetching stops: ${vehiclePositionResponse.message()}")
            }
            vehiclePositionResult
        }

}