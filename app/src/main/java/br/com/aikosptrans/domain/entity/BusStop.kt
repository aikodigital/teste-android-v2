package br.com.aikosptrans.domain.entity

data class BusStop(
    val busStopId: Int,
    val name: String,
    val address: String,
    val latitude: Double,
    val longitude: Double
)
