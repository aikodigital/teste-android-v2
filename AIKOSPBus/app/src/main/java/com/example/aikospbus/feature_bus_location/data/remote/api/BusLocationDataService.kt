package com.example.aikospbus.feature_bus_location.data.remote.api

import com.example.aikospbus.feature_api_sp_trans.remote.api.SPTransApi
import com.example.aikospbus.feature_bus_location.data.remote.dto.BusDto
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer

interface BusLocationDataService {

    suspend fun requestBusLocationData(cookie: String, lineCode: Int): BusDto?

    companion object {
        fun create(): BusLocationDataService {
            return BusLocationDataServiceImpl(
                apiService = SPTransApi.retrofitService
            )
        }
    }

}