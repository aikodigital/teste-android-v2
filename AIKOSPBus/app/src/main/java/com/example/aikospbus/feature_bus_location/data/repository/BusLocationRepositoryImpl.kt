package com.example.aikospbus.feature_bus_location.data.repository

import com.example.aikospbus.feature_bus_location.data.data_source.BusLocationDataSource
import com.example.aikospbus.feature_bus_location.domain.model.BusLocation
import javax.inject.Inject

class BusLocationRepositoryImpl @Inject constructor(
    private val localDataSource: BusLocationDataSource,
) : BusLocationRepository {

    override suspend fun insertBusLocation(busLocation: BusLocation) {
        localDataSource.insertBusLocation(busLocation)
    }

    override suspend fun insertBusLocationList(busLocationList: List<BusLocation>) {
        localDataSource.insertBusLocationList(busLocationList)
    }

    override suspend fun getBusLocationWords() = localDataSource.getBusLocationWords()

    override suspend fun updateBusLocation(busLocation: BusLocation) {
        localDataSource.updateBusLocation(busLocation)
    }
}