package com.leonardolino.busfinder.data.model


import com.google.gson.annotations.SerializedName
import com.leonardolino.busfinder.domain.model.VehiclePosition

data class VehiclePositionDto(
    @SerializedName("hr") val referenceTime: String,
    @SerializedName("vs") val vehicles: List<Vehicle>
) {
    data class Vehicle(
        @SerializedName("a") val isAccessible: Boolean,
        @SerializedName("p") val prefix: String,
        @SerializedName("px") val longitude: Double,
        @SerializedName("py") val latitude: Double,
        @SerializedName("ta") val universalTime: String
    ) {
        fun toVehicle() =
            VehiclePosition.Vehicle(isAccessible, prefix, longitude, latitude, universalTime)
    }

    fun toVehiclePosition() = VehiclePosition(referenceTime, vehicles.map { it.toVehicle() })
}