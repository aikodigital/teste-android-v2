package com.example.aikospbus.feature_bus_stops.data.remote.api

import com.example.aikospbus.feature_api_sp_trans.remote.api.SPTransApi
import com.example.aikospbus.feature_bus_stops.data.remote.dto.BusStopsDto

interface BusStopsDataService {

    suspend fun requestBusStopsData(cookie: String, searchTerms: String): List<BusStopsDto>?

    companion object {
        fun create(): BusStopsDataService {
            return BusStopsDataServiceImpl(
                apiService = SPTransApi.retrofitService
            )
        }
    }
}