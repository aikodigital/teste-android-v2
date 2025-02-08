package hopeapps.dedev.sptrans.domain.repository

import hopeapps.dedev.sptrans.data.network.ApiResponse
import hopeapps.dedev.sptrans.data.model.BusLine
import hopeapps.dedev.sptrans.data.model.BusStop

interface SearchRepository {
    suspend fun authApi(): Boolean
    suspend fun searchBusLines(query: String): ApiResponse<List<BusLine>>
    suspend fun searchBusStop(query: String): ApiResponse<List<BusStop>>
    suspend fun searchBusStopByBusLineId(idBusLine: Int): ApiResponse<List<BusStop>>
}