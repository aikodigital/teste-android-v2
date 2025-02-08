package hopeapps.dedev.sptrans.domain.repository

import hopeapps.dedev.sptrans.data.network.ApiResponse
import hopeapps.dedev.sptrans.domain.models.BusLine
import hopeapps.dedev.sptrans.domain.models.BusPrediction
import hopeapps.dedev.sptrans.domain.models.BusStop

interface SearchRepository {
    suspend fun authApi(): Boolean
    suspend fun searchBusLines(query: String): ApiResponse<List<BusLine>>
    suspend fun searchBusStop(query: String): ApiResponse<List<BusStop>>
    suspend fun searchBusStopByBusLineId(idBusLine: Int): ApiResponse<List<BusStop>>
    suspend fun searchBusStopPrediction(idBusStop: Int): ApiResponse<List<BusPrediction>>
}