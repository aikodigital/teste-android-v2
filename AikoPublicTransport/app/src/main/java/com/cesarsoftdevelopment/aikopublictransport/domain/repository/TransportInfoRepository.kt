package com.cesarsoftdevelopment.aikopublictransport.domain.repository

import com.cesarsoftdevelopment.aikopublictransport.data.model.BusLineEntity
import kotlinx.coroutines.flow.Flow

interface TransportInfoRepository {
    suspend fun saveBusLine(article : BusLineEntity)
    fun getSavedBusLines() : Flow<List<BusLineEntity>>

}