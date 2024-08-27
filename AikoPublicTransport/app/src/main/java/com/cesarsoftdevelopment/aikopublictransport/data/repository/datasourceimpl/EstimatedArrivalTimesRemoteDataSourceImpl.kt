package com.cesarsoftdevelopment.aikopublictransport.data.repository.datasourceimpl

import com.cesarsoftdevelopment.aikopublictransport.data.model.EstimatedArrivalTime
import com.cesarsoftdevelopment.aikopublictransport.data.network.PublicTransportApi
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.EstimatedArrivalTimesRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response

class EstimatedArrivalTimesRemoteDataSourceImpl(
    private val publicTransportApi: PublicTransportApi

) : EstimatedArrivalTimesRemoteDataSource {

    override suspend fun getEstimatedArrivalTimesByStop(stopCode: Long?): Response<EstimatedArrivalTime> {

        return withContext(Dispatchers.IO) {

            try {
                val response = publicTransportApi.getEstimatedArrivalTimesByStop(stopCode)

                if (response.isSuccessful) {
                    val estimatedArrivalTime = response.body()
                    if (estimatedArrivalTime != null) {
                        Response.success(estimatedArrivalTime)
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