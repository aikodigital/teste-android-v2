package hopeapps.dedev.sptrans.data.repository

import hopeapps.dedev.sptrans.data.network.ApiService
import hopeapps.dedev.sptrans.domain.mappers.toBusLineDomain
import hopeapps.dedev.sptrans.domain.mappers.toBusStopDomain
import hopeapps.dedev.sptrans.domain.mappers.toBusStopPredictionDomain
import hopeapps.dedev.sptrans.domain.mappers.toDynamicPointsDomain
import hopeapps.dedev.sptrans.domain.models.BusLine
import hopeapps.dedev.sptrans.domain.models.BusPrediction
import hopeapps.dedev.sptrans.domain.models.BusStop
import hopeapps.dedev.sptrans.domain.models.DynamicPoint
import hopeapps.dedev.sptrans.domain.repository.SearchRepository

class SearchRepositoryImpl(
    private val apiService: ApiService
) : SearchRepository {

    override suspend fun searchBusLines(query: String): List<BusLine> =
        apiService.getBusLinesWithNameOrNumber(query).toBusLineDomain()

    override suspend fun authApi(): Boolean = apiService.authApi()

    override suspend fun searchBusStop(query: String): List<BusStop> =
        apiService.getBusStopWithAddressOrName(query).toBusStopDomain()

    override suspend fun searchBusStopByBusLineId(idBusLine: Int): List<BusStop> =
        apiService.getBusByBusLineId(idBusLine.toString()).toBusStopDomain()

    override suspend fun searchBusStopPrediction(idBusStop: Int): List<BusPrediction> =
        apiService.getForecastWithBusStopCode(idBusStop.toString()).toBusStopPredictionDomain()

    override suspend fun searchAllBusMapPoints(): List<DynamicPoint> =
        apiService.getAllVehiclesPosition().toDynamicPointsDomain()
}