package com.leonardolino.busfinder.data.repository

import com.leonardolino.busfinder.data.api.ApiService
import com.leonardolino.busfinder.domain.model.BusStop
import com.leonardolino.busfinder.domain.model.EstimatedArrival
import com.leonardolino.busfinder.domain.model.LineInfo
import com.leonardolino.busfinder.domain.model.VehiclePosition
import com.leonardolino.busfinder.domain.repository.BusRepository

class BusRepositoryImpl(private val service: ApiService) : BusRepository {
    override suspend fun authenticate(token: String): Boolean {
        return service.authenticate(token).body() ?: false
    }

    override suspend fun getBusStops(terms: String): List<BusStop> {
        return service.getBusStops(terms).map { it.toBusStop() }
    }

    override suspend fun getNextArrivals(stopCode: Int): EstimatedArrival {
        return service.getNextArrivals(stopCode).toEstimatedArrival()
    }

    override suspend fun getVehiclesPosition(lineCode: Int): VehiclePosition {
        return service.getVehiclesPosition(lineCode).toVehiclePosition()
    }

    override suspend fun getLineInfo(terms: String, lineDirection: Int): List<LineInfo> {
        return service.getLineInfo(terms, lineDirection).map { it.toLineInfo() }
    }
}