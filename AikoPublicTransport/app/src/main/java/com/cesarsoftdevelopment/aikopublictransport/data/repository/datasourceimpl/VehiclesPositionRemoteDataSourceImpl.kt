package com.cesarsoftdevelopment.aikopublictransport.data.repository.datasourceimpl

import com.cesarsoftdevelopment.aikopublictransport.data.model.VehiclePosition
import com.cesarsoftdevelopment.aikopublictransport.data.network.PublicTransportApi
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.VehiclesPositionRemoteDataSource
import retrofit2.Response

class VehiclesPositionRemoteDataSourceImpl(private val publicTransportApi: PublicTransportApi) : VehiclesPositionRemoteDataSource {
    override suspend fun getVehiclesPositionByLine(lineCode: Int): Response<VehiclePosition> {
        TODO("Not yet implemented")
    }


}