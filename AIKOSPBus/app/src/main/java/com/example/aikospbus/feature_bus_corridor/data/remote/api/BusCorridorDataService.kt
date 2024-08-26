package com.example.aikospbus.feature_bus_corridor.data.remote.api

import com.example.aikospbus.feature_api_sp_trans.remote.api.SPTransApi
import com.example.aikospbus.feature_bus_corridor.data.remote.dto.BusCorridorDto

interface BusCorridorDataService {

    suspend fun requestBusCorridorData(cookie: String): List<BusCorridorDto>?

    companion object {
        fun create(): BusCorridorDataService {
            return BusCorridorDataServiceImpl(
                apiService = SPTransApi.retrofitService
            )
        }
    }

}