package com.example.spbustracker.repository

import com.example.spbustracker.model.Vehicle

object OlhoVivoApi {

    fun getVehiclesByLine(lineNumber: String, callback: (List<Vehicle>) -> Unit) {
        // Exemplo de retorno (mock)
        val mockVehicles = listOf(
            Vehicle(1, lineNumber, -23.55052, -46.633308),
            Vehicle(2, lineNumber, -23.551620, -46.634408)
        )

        // Simular um callback assíncrono com os veículos retornados
        callback(mockVehicles)
    }
}

