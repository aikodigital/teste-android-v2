package com.leonardolino.busfinder.data.model

import com.google.gson.annotations.SerializedName
import com.leonardolino.busfinder.domain.model.EstimatedArrival

data class EstimatedArrivalDto(
    @SerializedName("hr") val referenceTime: String, @SerializedName("p") val stop: StopDto?
) {
    data class StopDto(
        @SerializedName("cp") val code: Int,
        @SerializedName("np") val name: String,
        @SerializedName("py") val latitude: Double,
        @SerializedName("px") val longitude: Double,
        @SerializedName("l") val lines: List<LineDto>
    ) {
        data class LineDto(
            @SerializedName("c") val fullSign: String,
            @SerializedName("cl") val code: Int,
            @SerializedName("sl") val operatingDirection: Int,
            @SerializedName("lt0") val destinationSign: String,
            @SerializedName("lt1") val originSign: String,
            @SerializedName("qv") val vehicleQuantity: Int,
            @SerializedName("vs") val vehicles: List<VehicleDto>
        ) {
            data class VehicleDto(
                @SerializedName("p") val prefix: String,
                @SerializedName("t") val expectedTime: String,
                @SerializedName("a") val isAccessible: Boolean,
                @SerializedName("ta") val universalTime: String,
                @SerializedName("py") val latitude: Double,
                @SerializedName("px") val longitude: Double
            ) {
                fun toVehicle() = EstimatedArrival.Stop.Line.Vehicle(
                    prefix, expectedTime, isAccessible, universalTime, latitude, longitude
                )
            }

            fun toLine() = EstimatedArrival.Stop.Line(fullSign,
                code,
                operatingDirection,
                destinationSign,
                originSign,
                vehicleQuantity,
                vehicles.map { it.toVehicle() })
        }

        fun toStop() =
            EstimatedArrival.Stop(code, name, latitude, longitude, lines.map { it.toLine() })
    }

    fun toEstimatedArrival() = EstimatedArrival(referenceTime, stop?.toStop())
}
