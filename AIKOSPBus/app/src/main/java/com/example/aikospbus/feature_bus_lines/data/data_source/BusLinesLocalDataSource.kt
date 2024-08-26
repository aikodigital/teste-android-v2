package com.example.aikospbus.feature_bus_lines.data.data_source

import com.example.aikospbus.feature_bus_lines.domain.model.BusLinesModel

class BusLinesLocalDataSource(private val busLinesDao: BusLinesDao) : BusLinesDataSource {

    override suspend fun insertBusLines(busLinesModel: List<BusLinesModel>) {
        return busLinesDao.insertBusLines(busLinesModel)
    }

    override suspend fun getBusLines(): List<BusLinesModel>? {
        return busLinesDao.getBusLines()
    }
}