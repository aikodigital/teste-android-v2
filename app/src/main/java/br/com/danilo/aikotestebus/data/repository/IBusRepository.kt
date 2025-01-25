package br.com.danilo.aikotestebus.data.repository

import br.com.danilo.aikotestebus.data.model.ArrivalForecastResponse
import br.com.danilo.aikotestebus.data.model.BusStopLineResponse
import br.com.danilo.aikotestebus.data.model.BusesPositionResponse
import br.com.danilo.aikotestebus.data.model.LineDetailResponse
import br.com.danilo.aikotestebus.data.model.StopDetailResponse

interface IBusRepository {

    suspend fun authenticator(): Boolean

    suspend fun getBusLine(query: String): List<LineDetailResponse>?

    suspend fun getBusesPosition(): BusesPositionResponse

    suspend fun getBusStop(query: String): List<StopDetailResponse>?

    suspend fun getArrivalForecastTime(idStop: String, idLine: String): ArrivalForecastResponse

    suspend fun getBusStopByLine(idLine: Int): List<BusStopLineResponse>?
}