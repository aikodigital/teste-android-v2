package com.example.aikospbus.feature_bus_stops.data.remote.api

import com.example.aikospbus.feature_api_sp_trans.remote.api.SPTransApiService
import com.example.aikospbus.feature_bus_stops.data.remote.dto.BusStopsDto
import retrofit2.HttpException

class BusStopsDataServiceImpl(private val apiService: SPTransApiService) : BusStopsDataService {

    override suspend fun requestBusStopsData(
        cookie: String,
        searchTerms: String
    ): List<BusStopsDto>? {
        return try {
            val response = apiService.getStops(cookie, searchTerms)
            response
        } catch (e: HttpException) {
            null
        } catch (e: Exception) {
            null
        }
    }
}