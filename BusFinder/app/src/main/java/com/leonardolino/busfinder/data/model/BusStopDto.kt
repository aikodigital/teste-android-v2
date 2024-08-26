package com.leonardolino.busfinder.data.model

import com.google.gson.annotations.SerializedName
import com.leonardolino.busfinder.domain.model.BusStop

data class BusStopDto(
    @SerializedName("cp") val code: Int,
    @SerializedName("ed") val address: String,
    @SerializedName("np") val name: String,
    @SerializedName("py") val latitude: Double,
    @SerializedName("px") val longitude: Double
) {
    fun toBusStop() = BusStop(code, address, name, latitude, longitude)
}