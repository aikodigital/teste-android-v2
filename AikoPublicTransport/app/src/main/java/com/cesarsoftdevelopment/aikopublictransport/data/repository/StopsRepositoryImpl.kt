package com.cesarsoftdevelopment.aikopublictransport.data.repository

import com.cesarsoftdevelopment.aikopublictransport.data.model.StopItem
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.StopsRemoteDataSource
import com.cesarsoftdevelopment.aikopublictransport.domain.repository.StopsRepository
import com.cesarsoftdevelopment.aikopublictransport.utils.Resource

class StopsRepositoryImpl(
    private val stopsRemoteDataSource: StopsRemoteDataSource
) : StopsRepository {
    override suspend fun getStopsByLine(lineCode: Int): Resource<List<StopItem>> {
        TODO("Not yet implemented")
    }


}