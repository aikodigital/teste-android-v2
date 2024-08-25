package com.example.aikospbus.feature_bus_lines.data.repository

import com.example.aikospbus.feature_bus_lines.domain.model.BusLinesModel
import com.example.aikospbus.util.Resource
import kotlinx.coroutines.flow.Flow


interface BusLinesRepository {

    suspend fun insertBusLines(busLinesModel: List<BusLinesModel>)

    suspend fun getBusLines() : List<BusLinesModel>?

    fun getRemoteBusLines(cookie: String, searchTerms: String): Flow<Resource<List<BusLinesModel>?>>
}