package com.cesarsoftdevelopment.aikopublictransport.data.repository.datasourceimpl

import com.cesarsoftdevelopment.aikopublictransport.data.database.BusLineDao
import com.cesarsoftdevelopment.aikopublictransport.data.model.BusLineEntity
import com.cesarsoftdevelopment.aikopublictransport.data.repository.datasource.TransportInfoLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class TransportInfoLocalDataSourceImpl(private val busLineDao: BusLineDao) : TransportInfoLocalDataSource {
    override suspend fun saveBusLineToDb(busLineEntity: BusLineEntity) {
        withContext(Dispatchers.IO) {
            busLineDao.insertBusLine(busLineEntity)
        }
    }

    override fun getSavedBusLines(): Flow<List<BusLineEntity>> {
        return busLineDao.getAllBusLines()
    }


}