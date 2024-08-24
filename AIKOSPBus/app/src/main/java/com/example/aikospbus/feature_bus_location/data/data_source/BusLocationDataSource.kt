package com.example.aikospbus.feature_bus_location.data.data_source

import com.example.aikospbus.feature_bus_location.data.remote.dto.BusDto
import com.example.aikospbus.feature_bus_location.domain.model.BusLocationModel

interface BusLocationDataSource {

    suspend fun insertBusLocation(busLocationModel: BusLocationModel)

    suspend fun getBusLocation(): BusLocationModel

    suspend fun updateBusLocation(busLocationModel: BusLocationModel)
}