package com.example.aikospbus.feature_bus_stops.data.repository

import com.example.aikospbus.feature_bus_stops.domain.model.BusStopsModel
import com.example.aikospbus.util.Resource
import kotlinx.coroutines.flow.Flow

interface BusStopsRepository {

    suspend fun insertBusStops(busStopsModel: List<BusStopsModel>)

    suspend fun getBusStops() : List<BusStopsModel>?

    fun getRemoteBusStops(cookie: String, searchTerms: String) : Flow<Resource<List<BusStopsModel>?>>
}