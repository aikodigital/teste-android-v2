package com.example.aikospbus.feature_bus_corridor.data.data_source

import com.example.aikospbus.feature_bus_corridor.domain.model.BusCorridorModel

interface BusCorridorDataSource {

    suspend fun insertBusCorridor(busCorridorModel: List<BusCorridorModel>)

    suspend fun getBusCorridor(): List<BusCorridorModel>?

}