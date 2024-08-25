package com.example.aikospbus.feature_bus_stops.data.data_source

import com.example.aikospbus.feature_bus_stops.domain.model.BusStopsModel

class BusStopsLocalDataSource(private val busStopsDao: BusStopsDao): BusStopsDataSource {

    override suspend fun insertBusStops(busStopsModel: List<BusStopsModel>) {
        return busStopsDao.insertBusStops(busStopsModel)
    }

    override suspend fun getBusStops(): List<BusStopsModel>? {
        return busStopsDao.getBusStops()
    }

}