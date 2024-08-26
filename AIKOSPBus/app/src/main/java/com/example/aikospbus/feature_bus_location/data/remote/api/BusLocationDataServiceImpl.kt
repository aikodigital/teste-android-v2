package com.example.aikospbus.feature_bus_location.data.remote.api

import com.example.aikospbus.feature_api_sp_trans.remote.api.SPTransApiService
import com.example.aikospbus.feature_bus_location.data.remote.dto.BusDto
import retrofit2.HttpException


class BusLocationDataServiceImpl(private val apiService: SPTransApiService) :
    BusLocationDataService {

    override suspend fun requestBusLocationData(cookie: String, lineCode: Int): BusDto? {
        return try {
            val response = apiService.getLinePosition(cookie, lineCode)
            response
        } catch (e: HttpException) {
            null
        } catch (e: Exception) {
            null
        }
    }
}