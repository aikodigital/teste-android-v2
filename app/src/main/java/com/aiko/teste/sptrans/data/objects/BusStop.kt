package com.aiko.teste.sptrans.data.objects

import com.google.gson.annotations.SerializedName

data class BusStop(
    @SerializedName("cp") val stopCode: String,
    @SerializedName("np") val stopName: String,
    @SerializedName("py") val latitude: Double,
    @SerializedName("px") val longitude: Double,
    @SerializedName("l") val lines: List<Line>
) {
    override fun toString(): String {
        return "BusStop(stopCode='$stopCode', stopName='$stopName', latitude=$latitude, longitude=$longitude, lines=$lines)"
    }
}