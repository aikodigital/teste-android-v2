package br.com.danilo.aikotestebus.data.repository

import br.com.danilo.aikotestebus.data.model.ArrivalForecastResponse
import br.com.danilo.aikotestebus.data.model.BusesPositionResponse
import br.com.danilo.aikotestebus.data.model.LineDetailResponse
import br.com.danilo.aikotestebus.data.model.StopDetailResponse
import br.com.danilo.aikotestebus.data.service.BusApiService

internal class BusRepositoryImpl(
    private val service: BusApiService
) : IBusRepository {

    override suspend fun authenticator(): Boolean {
        return service.authenticator()
    }

    override suspend fun getBusLine(query: String): List<LineDetailResponse>? {
        return service.getBusLine(query)
    }

    override suspend fun getBusesPosition(): BusesPositionResponse {
        return service.getBusesPosition()
    }

    override suspend fun getBusStop(query: String): List<StopDetailResponse>? {
        return service.getBusStop(query)
    }

    override suspend fun getArrivalForecastTime(
        idStop: String,
        idLine: String
    ): ArrivalForecastResponse {
        return service.getArrivalForecastTime(idStop, idLine)
    }
}