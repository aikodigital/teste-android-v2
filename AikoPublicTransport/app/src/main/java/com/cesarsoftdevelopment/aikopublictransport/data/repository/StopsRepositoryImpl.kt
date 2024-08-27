package com.cesarsoftdevelopment.aikopublictransport.data.repository

import com.cesarsoftdevelopment.aikopublictransport.data.model.StopItem
import com.cesarsoftdevelopment.aikopublictransport.data.model.VehiclePosition
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.StopsRemoteDataSource
import com.cesarsoftdevelopment.aikopublictransport.domain.repository.StopsRepository
import com.cesarsoftdevelopment.aikopublictransport.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class StopsRepositoryImpl(private val stopsRemoteDataSource: StopsRemoteDataSource) : StopsRepository {
    override suspend fun getStopsByLine(lineCode: Int): Resource<List<StopItem>?> = coroutineScope {

        val stops = async(Dispatchers.IO) {
            stopsRemoteDataSource.getStopsByLine(lineCode)
        }

        val stopsResponse = stops.await()

        val stopsResult = if (stopsResponse.isSuccessful) {
            Resource.Success(stopsResponse.body())
        } else {
            Resource.Error("Error fetching stops: ${stopsResponse.message()}")
        }
        stopsResult
    }

}