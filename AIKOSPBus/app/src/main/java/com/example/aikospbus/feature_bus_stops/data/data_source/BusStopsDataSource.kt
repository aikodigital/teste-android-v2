package com.example.aikospbus.feature_bus_stops.data.data_source

import com.example.aikospbus.feature_bus_stops.domain.model.BusStopsModel

interface BusStopsDataSource {

    suspend fun insertBusStops(busStopsModel: List<BusStopsModel>)

    suspend fun getBusStops(): BusStopsModel
}