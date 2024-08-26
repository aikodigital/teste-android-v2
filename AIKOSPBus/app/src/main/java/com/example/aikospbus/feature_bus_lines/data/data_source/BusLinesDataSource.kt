package com.example.aikospbus.feature_bus_lines.data.data_source

import com.example.aikospbus.feature_bus_lines.domain.model.BusLinesModel

interface BusLinesDataSource {

    suspend fun insertBusLines(busLinesModel: List<BusLinesModel>)

    suspend fun getBusLines(): List<BusLinesModel>?
}