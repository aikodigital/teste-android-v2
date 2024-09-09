package com.example.spbustracker.model

data class Vehicle(
    val p: Int,       // Prefixo do veículo
    val a: Boolean,   // Acessibilidade
    val ta: String,   // Horário da localização
    val py: Double,   // Latitude
    val px: Double    // Longitude
)
