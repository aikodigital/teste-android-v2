package com.leonardolino.busfinder.domain.model

data class BusStop(
    val code: Int,
    val address: String,
    val name: String,
    val latitude: Double,
    val longitude: Double
)
