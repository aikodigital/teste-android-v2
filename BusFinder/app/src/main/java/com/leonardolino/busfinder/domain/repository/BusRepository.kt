package com.leonardolino.busfinder.domain.repository

import com.leonardolino.busfinder.domain.model.BusStop
import com.leonardolino.busfinder.domain.model.EstimatedArrival
import com.leonardolino.busfinder.domain.model.LineInfo
import com.leonardolino.busfinder.domain.model.VehiclePosition

interface BusRepository {
    suspend fun authenticate(token: String): Boolean

    suspend fun getBusStops(terms: String): List<BusStop>

    suspend fun getNextArrivals(stopCode: Int): EstimatedArrival

    suspend fun getVehiclesPosition(lineCode: Int): VehiclePosition

    suspend fun getLineInfo(terms: String, lineDirection: Int): List<LineInfo>
}