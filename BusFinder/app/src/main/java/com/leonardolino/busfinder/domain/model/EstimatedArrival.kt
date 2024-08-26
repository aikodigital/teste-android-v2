package com.leonardolino.busfinder.domain.model

data class EstimatedArrival(
    val referenceTime: String, val stop: Stop?
) {
    data class Stop(
        val code: Int,
        val name: String,
        val latitude: Double,
        val longitude: Double,
        val lines: List<Line>
    ) {
        data class Line(
            val fullSign: String,
            val code: Int,
            val operationDirection: Int,
            val destinationSign: String,
            val originSign: String,
            val vehicleQuantity: Int,
            val vehicles: List<Vehicle>
        ) {
            data class Vehicle(
                val prefix: String,
                val expectedTime: String,
                val isAccessible: Boolean,
                val universalTime: String,
                val latitude: Double,
                val longitude: Double
            )
        }
    }
}
