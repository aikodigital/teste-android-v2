package com.example.aikospbus.data.source

import com.example.aikospbus.feature_bus_location.data.data_source.BusLocationDataSource
import com.example.aikospbus.feature_bus_location.domain.model.BusLocationModel

class FakeBusLocationDataSource(private var busLocation: BusLocationModel) : BusLocationDataSource {

    override suspend fun insertBusLocation(busLocationModel: BusLocationModel) {
        busLocation = busLocationModel
    }

    override suspend fun getBusLocation(): BusLocationModel {
       return busLocation
    }

    override suspend fun updateBusLocation(busLocationModel: BusLocationModel) {
        busLocation = busLocationModel
    }

}