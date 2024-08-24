package com.example.aikospbus.feature_bus_location.data.data_source

import com.example.aikospbus.feature_bus_location.domain.model.BusLocation

class BusLocationLocalDataSource(private val busLocationDao: BusLocationDao) : BusLocationDataSource {
    override suspend fun insertBusLocation(busLocation: BusLocation) {
       return busLocationDao.insertBusLocation(busLocation)
    }

    override suspend fun insertBusLocationList(busLocationList: List<BusLocation>) {
        return busLocationDao.insertBusLocationList(busLocationList)
    }

    override suspend fun getBusLocationWords(): List<BusLocation> {
        return busLocationDao.getBusLocationWords()
    }

    override suspend fun updateBusLocation(busLocation: BusLocation) {
        return busLocationDao.updateBusLocation(busLocation)
    }
}