package com.example.spbustracker.model

data class VehiclePositionResponse(
    val hr: String,       // Horário de referência
    val l: List<Line>     // Lista de linhas
)
