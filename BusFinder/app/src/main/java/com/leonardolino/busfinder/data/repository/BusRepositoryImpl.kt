package com.leonardolino.busfinder.data.repository

import com.leonardolino.busfinder.data.api.ApiService
import com.leonardolino.busfinder.domain.model.BusStop
import com.leonardolino.busfinder.domain.model.NextArrivals
import com.leonardolino.busfinder.domain.repository.BusRepository

class BusRepositoryImpl(private val service: ApiService) : BusRepository {
    override suspend fun authenticate(token: String): Boolean {
        return service.authenticate(token).body() ?: false
    }

    override suspend fun getBusStops(terms: String): List<BusStop> {
        return service.getBusStops(terms).map { it.toDomainModel() }
    }

    override suspend fun getNextArrivals(stopCode: Int): NextArrivals {
        return service.getNextArrivals(stopCode).toNextArrivals()
    }
}