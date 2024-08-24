package com.example.aikospbus.feature_bus_location.data.repository

import com.example.aikospbus.feature_bus_location.data.remote.dto.BusDto
import com.example.aikospbus.feature_bus_location.domain.model.BusLocationModel
import com.example.aikospbus.util.Resource
import kotlinx.coroutines.flow.Flow

interface BusLocationRepository {

    suspend fun insertBusLocation(busLocationModel: BusLocationModel)

    suspend fun getBusLocation() : BusLocationModel

    suspend fun updateBusLocation(busLocationModel: BusLocationModel)

    fun getRemoteBusLocation(cookie: String, lineCode: Int): Flow<Resource<BusLocationModel>>
}