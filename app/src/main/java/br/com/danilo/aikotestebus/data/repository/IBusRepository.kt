package br.com.danilo.aikotestebus.data.repository

import br.com.danilo.aikotestebus.data.model.ArrivalForecastResponse
import br.com.danilo.aikotestebus.data.model.BusStopLineResponse
import br.com.danilo.aikotestebus.data.model.BusesPositionResponse
import br.com.danilo.aikotestebus.data.model.LineDetailResponse

interface IBusRepository {

    suspend fun authenticator(): Boolean

    suspend fun getBusLine(query: String): List<LineDetailResponse>?

    suspend fun getBusesPosition(): BusesPositionResponse?

    suspend fun getArrivalForecastTime(idStop: Int, idLine: Int): ArrivalForecastResponse?

    suspend fun getBusStopByLine(idLine: Int): List<BusStopLineResponse>?
}