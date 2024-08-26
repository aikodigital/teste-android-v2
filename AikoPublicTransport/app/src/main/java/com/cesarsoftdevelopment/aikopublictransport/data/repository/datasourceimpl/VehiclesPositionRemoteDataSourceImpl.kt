package com.cesarsoftdevelopment.aikopublictransport.data.repository.datasourceimpl

import com.cesarsoftdevelopment.aikopublictransport.data.model.StopItem
import com.cesarsoftdevelopment.aikopublictransport.data.model.VehiclePosition
import com.cesarsoftdevelopment.aikopublictransport.data.network.PublicTransportApi
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.VehiclesPositionRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response

class VehiclesPositionRemoteDataSourceImpl(private val publicTransportApi: PublicTransportApi) : VehiclesPositionRemoteDataSource {
    override suspend fun getVehiclesPositionByLine(lineCode: Int): Response<VehiclePosition> {

        return withContext(Dispatchers.IO) {
            try {
                val response = publicTransportApi.getVehiclesPositionByLine(lineCode)

                if (response.isSuccessful) {
                    val vehicleObject = response.body()
                    if (vehicleObject != null) {
                        Response.success(vehicleObject)
                    } else {
                        Response.error(404, ResponseBody.create(null, "Bus lines not found"))
                    }
                } else {
                    Response.error(response.code(), response.errorBody() ?: ResponseBody.create(null, "Unknown error"))
                }

            } catch (err: Exception) {
                Response.error(500, ResponseBody.create(null, "Exception occurred: ${err.message}"))
            }
        }
    }


}