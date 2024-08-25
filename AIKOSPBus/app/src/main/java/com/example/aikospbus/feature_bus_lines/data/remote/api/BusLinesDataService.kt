package com.example.aikospbus.feature_bus_lines.data.remote.api

import com.example.aikospbus.feature_api_sp_trans.remote.api.SPTransApi
import com.example.aikospbus.feature_bus_lines.data.remote.dto.BusLinesDto

interface BusLinesDataService {

    suspend fun requestBusLinesData(cookie: String, searchTerms: String): List<BusLinesDto>?

    companion object {
        fun create(): BusLinesDataService {
            return BusLinesDataServiceImpl(
                apiService = SPTransApi.retrofitService
            )
        }
    }
}