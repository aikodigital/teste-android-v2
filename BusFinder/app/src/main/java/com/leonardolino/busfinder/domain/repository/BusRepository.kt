package com.leonardolino.busfinder.domain.repository

import com.leonardolino.busfinder.domain.model.BusStop

interface BusRepository {
    suspend fun authenticate(token: String): Boolean

    suspend fun getBusStops(terms: String): List<BusStop>
}