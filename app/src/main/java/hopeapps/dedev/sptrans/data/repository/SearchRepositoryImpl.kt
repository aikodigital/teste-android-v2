package hopeapps.dedev.sptrans.data.repository

import hopeapps.dedev.sptrans.data.models.BusStopPredictionDto
import hopeapps.dedev.sptrans.data.network.ApiResponse
import hopeapps.dedev.sptrans.data.network.ApiService
import hopeapps.dedev.sptrans.domain.mappers.toBusLineDomain
import hopeapps.dedev.sptrans.domain.mappers.toBusStopDomain
import hopeapps.dedev.sptrans.domain.mappers.toBusStopPredictionDomain
import hopeapps.dedev.sptrans.domain.models.BusLine
import hopeapps.dedev.sptrans.domain.models.BusPrediction
import hopeapps.dedev.sptrans.domain.models.BusStop
import hopeapps.dedev.sptrans.domain.repository.SearchRepository
import java.io.IOException

class SearchRepositoryImpl(
    private val apiService: ApiService
) : SearchRepository {

    override suspend fun searchBusLines(query: String): ApiResponse<List<BusLine>> {
        return try {
            val response = apiService.getBusLinesWithNameOrNumber(query)
            if (response.isNotEmpty()) {
                ApiResponse.Success(response.toBusLineDomain())
            } else {
                ApiResponse.Error(404)
            }
        } catch (e: IOException) {
            ApiResponse.Error(0)
        }
    }

    override suspend fun authApi(): Boolean = apiService.authApi()

    override suspend fun searchBusStop(query: String): ApiResponse<List<BusStop>> {
        return try {
            val response = apiService.getBusStopWithAddressOrName(query)
            if (response.isNotEmpty()) {
                ApiResponse.Success(response.toBusStopDomain())
            } else {
                ApiResponse.Error(404)
            }
        } catch (e: IOException) {
            ApiResponse.Error(0)
        }
    }

    override suspend fun searchBusStopByBusLineId(idBusLine: Int): ApiResponse<List<BusStop>> {
        return try {
            val response = apiService.getBusByBusLineId(idBusLine.toString())
            if (response.isNotEmpty()) {
                ApiResponse.Success(response.toBusStopDomain())
            } else {
                ApiResponse.Error(404)
            }
        } catch (e: IOException) {
            ApiResponse.Error(0)
        }
    }

    override suspend fun searchBusStopPrediction(idBusStop: Int): ApiResponse<List<BusPrediction>> {
        return try {
            val response = apiService.getForecastWithBusStopCode(idBusStop.toString())
            if (response != null) {
                ApiResponse.Success(response.toBusStopPredictionDomain())
            } else {
                ApiResponse.Error(404)
            }
        } catch (e: IOException) {
            ApiResponse.Error(0)
        }
    }
}