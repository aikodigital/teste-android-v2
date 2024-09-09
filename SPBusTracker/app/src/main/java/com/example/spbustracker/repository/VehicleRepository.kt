package com.example.spbustracker.repository

import android.util.Log
import com.example.spbustracker.model.Vehicle
import com.example.spbustracker.network.SPTransApiService

class VehicleRepository(private val api: SPTransApiService) {

    suspend fun getVehiclePositions(): List<Vehicle>? {
        val response = api.getVehiclePositions()
        return if (response.isSuccessful) {
            response.body()?.l?.flatMap { it.vs }
        } else {
            Log.e("API_ERROR", "Erro ao buscar posições: ${response.errorBody()?.string()}")
            throw Exception("Erro ao buscar posições: ${response.errorBody()?.string()}")
        }
    }

}

