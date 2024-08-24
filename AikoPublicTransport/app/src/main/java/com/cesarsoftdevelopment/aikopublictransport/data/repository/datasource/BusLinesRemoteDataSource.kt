package com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource

import com.cesarsoftdevelopment.aikopublictransport.data.model.BusLineItem
import retrofit2.Response

interface BusLinesRemoteDataSource {
    suspend fun getBusLines(termsSearch: String): Response<List<BusLineItem>>
}