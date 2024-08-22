package com.aiko.teste.sptrans.data.repositories

import com.aiko.teste.sptrans.data.APIService
import com.aiko.teste.sptrans.data.objects.BusStop
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BusStopsRepository @Inject constructor(private val apiService: APIService) {
    private var cachedData: List<BusStop>? = null
    fun getBusStops(): Result<List<BusStop>> {
        return try {
            val response = apiService.getBusStops("").execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    cachedData = it
                    Result.success(it)
                } ?: Result.failure(Exception("Response body is null"))
            } else {
                Result.failure(Exception("API error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}