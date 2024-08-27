package com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource

import com.cesarsoftdevelopment.aikopublictransport.data.model.BusLineEntity
import kotlinx.coroutines.flow.Flow

interface TransportInfoLocalDataSource {
    suspend fun saveBusLineToDb(busLineEntity: BusLineEntity)
    fun getSavedBusLines() : Flow<List<BusLineEntity>>

}