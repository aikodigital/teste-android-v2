package com.cesarsoftdevelopment.aikopublictransport.data.repository.datasourceimpl

import com.cesarsoftdevelopment.aikopublictransport.data.model.BusLineItem
import com.cesarsoftdevelopment.aikopublictransport.data.model.StopItem
import com.cesarsoftdevelopment.aikopublictransport.data.network.PublicTransportApi
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.StopsRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
class StopsRemoteDataSourceImpl(private val publicTransportApi: PublicTransportApi) : StopsRemoteDataSource {

    override suspend fun getStopsByLine(lineCode: Int): Response<List<StopItem>> {

        return withContext(Dispatchers.IO) {

            try {
                val response = publicTransportApi.getStopsByLine(lineCode)

                if (response.isSuccessful) {
                    val stops = response.body()
                    if (stops != null) {
                        Response.success(stops)
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