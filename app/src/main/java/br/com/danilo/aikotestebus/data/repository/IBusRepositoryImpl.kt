package br.com.danilo.aikotestebus.data.repository

import br.com.danilo.aikotestebus.data.model.LineDetailResponse
import br.com.danilo.aikotestebus.data.service.BusApiService

internal class IBusRepositoryImpl(
    private val service: BusApiService
) : IBusRepository {

    override suspend fun getBusLine(query: String): List<LineDetailResponse?> {
        return service.getBusLine(query)
    }

    override suspend fun authenticator(): Boolean {
        return service.authenticator()
    }
}