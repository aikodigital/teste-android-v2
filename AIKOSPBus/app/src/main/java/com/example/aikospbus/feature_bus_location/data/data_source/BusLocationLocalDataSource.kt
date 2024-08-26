package com.example.aikospbus.feature_bus_location.data.data_source

import com.example.aikospbus.feature_bus_location.domain.model.BusLocationModel

class BusLocationLocalDataSource(private val busLocationDao: BusLocationDao) :
    BusLocationDataSource {
    override suspend fun insertBusLocation(busLocationModel: BusLocationModel) {
        return busLocationDao.insertBusLocation(busLocationModel)
    }

    override suspend fun getBusLocation(): BusLocationModel {
        return busLocationDao.getBusLocation()
    }

    override suspend fun updateBusLocation(busLocationModel: BusLocationModel) {
        return busLocationDao.updateBusLocation(busLocationModel)
    }
}