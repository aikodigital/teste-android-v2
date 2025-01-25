package br.com.danilo.aikotestebus.data.repository

import br.com.danilo.aikotestebus.data.model.LineDetailResponse

interface IBusRepository {

    suspend fun getBusLine(query: String): List<LineDetailResponse?>

    suspend fun authenticator() : Boolean
}