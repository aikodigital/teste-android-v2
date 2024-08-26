package com.leonardolino.busfinder.domain.model


import com.google.gson.annotations.SerializedName

data class VehiclePosition(
    @SerializedName("hr") val referenceTime: String,
    @SerializedName("vs") val vehicles: List<Vehicle>
) {
    data class Vehicle(
        @SerializedName("a") val isAccessible: Boolean,
        @SerializedName("p") val prefix: String,
        @SerializedName("px") val longitude: Double,
        @SerializedName("py") val latitude: Double,
        @SerializedName("ta") val universalTime: String
    )
}