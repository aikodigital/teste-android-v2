package com.leonardolino.busfinder.data.repository

import android.util.Log
import com.leonardolino.busfinder.data.api.ApiService
import com.leonardolino.busfinder.domain.model.BusStop
import com.leonardolino.busfinder.domain.repository.BusRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BusRepositoryImpl(private val service: ApiService) : BusRepository {
    override suspend fun authenticate(token: String): Boolean {
        return withContext(Dispatchers.IO){
            try {
                service.authenticate(token).body() ?: false
            } catch (e: Exception) {
                Log.e("authentication", "Authentication failed - ${e.message ?: "Unknown Error"}")
                false
            }
        }
    }

    override suspend fun getBusStops(terms: String): List<BusStop> {
        return withContext(Dispatchers.IO) {
            try {
                service.getBusStops(terms).map { it.toDomainModel() }
            } catch (e: Exception) {
                Log.e("busstops", "Error fetching bus stops - ${e.message}")
                emptyList()
            }
        }
    }
}