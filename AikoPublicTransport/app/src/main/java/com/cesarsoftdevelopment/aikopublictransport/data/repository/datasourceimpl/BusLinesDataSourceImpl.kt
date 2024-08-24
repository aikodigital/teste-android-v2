package com.cesarsoftdevelopment.aikopublictransport.data.repository.datasourceimpl

import com.cesarsoftdevelopment.aikopublictransport.data.model.BusLineItem
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.BusLinesRemoteDataSource
import retrofit2.Response

class BusLinesDataSourceImpl : BusLinesRemoteDataSource {
    override suspend fun getBusLines(termsSearch: String): Response<List<BusLineItem>> {
        TODO("Not yet implemented")
    }
}