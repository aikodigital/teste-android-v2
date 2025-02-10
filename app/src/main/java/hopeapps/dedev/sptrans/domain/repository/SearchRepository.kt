package hopeapps.dedev.sptrans.domain.repository

import hopeapps.dedev.sptrans.domain.models.BusLine
import hopeapps.dedev.sptrans.domain.models.BusPrediction
import hopeapps.dedev.sptrans.domain.models.BusStop
import hopeapps.dedev.sptrans.domain.models.DynamicPoint

interface SearchRepository {
    suspend fun authApi(): Boolean
    suspend fun searchBusLines(query: String): List<BusLine>
    suspend fun searchBusStop(query: String): List<BusStop>
    suspend fun searchBusStopByBusLineId(idBusLine: Int): List<BusStop>
    suspend fun searchBusStopPrediction(idBusStop: Int): List<BusPrediction>
    suspend fun searchAllBusMapPoints(): List<DynamicPoint>
}