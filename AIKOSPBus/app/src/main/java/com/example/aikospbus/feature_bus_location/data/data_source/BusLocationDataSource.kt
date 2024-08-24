package com.example.aikospbus.feature_bus_location.data.data_source

import com.example.aikospbus.feature_bus_location.domain.model.BusLocation

interface BusLocationDataSource {

    suspend fun insertBusLocation(busLocation: BusLocation)

    suspend fun insertBusLocationList(busLocationList: List<BusLocation>)

    suspend fun getBusLocationWords(): List<BusLocation>

    suspend fun updateBusLocation(busLocation: BusLocation)
}