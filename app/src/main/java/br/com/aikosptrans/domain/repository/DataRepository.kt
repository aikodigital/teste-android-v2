package br.com.aikosptrans.domain.repository

import br.com.aikosptrans.domain.entity.Bus
import br.com.aikosptrans.domain.entity.BusLine
import br.com.aikosptrans.domain.entity.BusStop

interface DataRepository {
    suspend fun authenticate(): Boolean
    suspend fun getBusesLocation(): List<Bus>
    suspend fun getBusStop(query: String): List<BusStop>
    suspend fun getBusLineDetail(query: String): List<BusLine>
}