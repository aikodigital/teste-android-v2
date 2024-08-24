package com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource

import com.cesarsoftdevelopment.aikopublictransport.data.model.StopItem
import retrofit2.Response
interface StopsRemoteDataSource {
    suspend fun getStopsByLine(lineCode: Int): Response<List<StopItem>>
}