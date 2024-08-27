package com.cesarsoftdevelopment.aikopublictransport.data.repository.datasourceimpl

import com.cesarsoftdevelopment.aikopublictransport.data.model.BusLineItem
import com.cesarsoftdevelopment.aikopublictransport.data.network.PublicTransportApi
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.BusLinesRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class BusLinesRemoteDataSourceImpl (private val publicTransportApi: PublicTransportApi) : BusLinesRemoteDataSource {
    override suspend fun getBusLines(termsSearch: String): Response<List<BusLineItem>> {

        return withContext(Dispatchers.IO) {
            try {
                val response = publicTransportApi.getBusLines(termsSearch)

                if (response.isSuccessful) {
                    val busLineList = response.body()
                    if (busLineList != null) {
                        Response.success(busLineList)
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