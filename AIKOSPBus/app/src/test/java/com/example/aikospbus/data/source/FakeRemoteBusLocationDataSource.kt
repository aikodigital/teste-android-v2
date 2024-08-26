package com.example.aikospbus.data.source

import com.example.aikospbus.feature_bus_location.data.remote.api.BusLocationDataService
import com.example.aikospbus.feature_bus_location.data.remote.dto.BusDto
import com.example.aikospbus.feature_bus_location.data.remote.dto.VehicleDto

class FakeRemoteBusLocationDataSource: BusLocationDataService {

    private val vehicleDtos =
        listOf(
            VehicleDto(
                123,
                true,
                "12:00",
                12.345,
                67.891,
                "teste"
            )
        )

    private var fakeBusLocationData: BusDto? = BusDto(
        "12:00",
        vehicleDtos,
    )

    fun mockNull() {
        fakeBusLocationData = null
    }

    override suspend fun requestBusLocationData(cookie: String, lineCode: Int): BusDto? {
        return fakeBusLocationData
    }
}