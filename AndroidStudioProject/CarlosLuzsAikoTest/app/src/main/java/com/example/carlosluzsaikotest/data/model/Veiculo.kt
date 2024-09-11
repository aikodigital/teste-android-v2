package com.example.carlosluzsaikotest.data.model

data class Veiculo(
    val p: Int,             // Prefixo do veículo
    val a: Boolean,         // Acessibilidade
    val ta: String,         // Horário da localização (ISO 8601)
    val py: Double,         // Latitude
    val px: Double          // Longitude
)
