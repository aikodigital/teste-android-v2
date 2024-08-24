package com.cesarsoftdevelopment.aikopublictransport.data.repository.datasourceimpl

import com.cesarsoftdevelopment.aikopublictransport.data.model.VehiclePosition
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.VehiclesPositionRemoteDataSource
import retrofit2.Response

class VehiclesPositionRemoteDataSourceImpl : VehiclesPositionRemoteDataSource {
    override suspend fun getVehiclesPositionByLine(lineCode: Int): Response<VehiclePosition> {
        TODO("Not yet implemented")
    }


}