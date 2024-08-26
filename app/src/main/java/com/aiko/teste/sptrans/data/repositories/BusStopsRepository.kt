package com.aiko.teste.sptrans.data.repositories

import com.aiko.teste.sptrans.data.APIService
import com.aiko.teste.sptrans.data.objects.BusStop
import com.aiko.teste.sptrans.data.objects.BusStopPrevisions
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BusStopsRepository @Inject constructor(private val apiService: APIService): BaseRepository() {
    var cachedData: List<BusStop>? = null
    fun getBusStops(): Result<List<BusStop>> {
        cachedData?.let { return Result.success(it) }

        return makeApiCall { apiService.getBusStops("").execute() }.also { result ->
            if (result.isSuccess) {
                cachedData = result.getOrNull()
            }
        }
    }

    fun getBusStops(searchTerms: String): Result<List<BusStop>> {
        return makeApiCall { apiService.getBusStops(searchTerms).execute() }
    }

    fun getBusStopsByLine(lineCode: String): Result<List<BusStop>> {
        return makeApiCall { apiService.getBusStopsByLine(lineCode).execute() }
    }

    fun getBusStop(busStopCode: String): BusStop {
        return cachedData!!.filter { it.stopCode == busStopCode }[0]
    }

    fun getBusStopPrevisions(busStopCode: String): Result<BusStopPrevisions> {
        return makeApiCall { apiService.getBusStopPrevisions(busStopCode).execute() }
    }
}