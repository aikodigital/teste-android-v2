package hopeapps.dedev.sptrans.data.repository

import hopeapps.dedev.sptrans.data.network.ApiResponse
import hopeapps.dedev.sptrans.data.model.BusLine
import hopeapps.dedev.sptrans.data.model.BusStop
import hopeapps.dedev.sptrans.data.network.ApiService
import hopeapps.dedev.sptrans.domain.repository.SearchRepository
import java.io.IOException

class SearchRepositoryImpl(
    private val apiService: ApiService
) : SearchRepository {

    override suspend fun searchBusLines(query: String): ApiResponse<List<BusLine>> {
        return try {
            val response = apiService.getBusLinesWithNameOrNumber(query)
            if (response.isNotEmpty()) {
                ApiResponse.Success(response)
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
                ApiResponse.Success(response)
            } else {
                ApiResponse.Error(404)
            }
        } catch (e: IOException) {
            ApiResponse.Error(0)
        }
    }
}