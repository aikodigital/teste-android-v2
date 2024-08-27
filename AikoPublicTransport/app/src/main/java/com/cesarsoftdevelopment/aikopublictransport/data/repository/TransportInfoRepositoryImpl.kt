package com.cesarsoftdevelopment.aikopublictransport.data.repository

import com.cesarsoftdevelopment.aikopublictransport.data.model.BusLineEntity
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.TransportInfoLocalDataSource
import com.cesarsoftdevelopment.aikopublictransport.domain.repository.TransportInfoRepository
import kotlinx.coroutines.flow.Flow

class TransportInfoRepositoryImpl(
    private val transportInfoLocalDataSource: TransportInfoLocalDataSource
) : TransportInfoRepository {
    override suspend fun saveBusLine(article: BusLineEntity) {
        transportInfoLocalDataSource.saveBusLineToDb(article)
    }

    override fun getSavedBusLines(): Flow<List<BusLineEntity>> {
        return transportInfoLocalDataSource.getSavedBusLines()
    }
}