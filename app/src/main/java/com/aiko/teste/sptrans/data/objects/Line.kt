package com.aiko.teste.sptrans.data.objects

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Line(
    @SerializedName("c") val code: String,
    @SerializedName("cl") val lineCode: String,
    @SerializedName("lt0") val lineStart: String,
    @SerializedName("lt1") val lineEnd: String,
    @SerializedName("vs") val positions: List<BusPosition>
): Serializable {
    override fun toString(): String {
        return "Line(code='$code', lineCode='$lineCode', lineStart='$lineStart', lineEnd='$lineEnd', positions=$positions)"
    }
}

data class BusPosition(
    @SerializedName("p") val busCode: String,
    @SerializedName("px") val longitude: Double,
    @SerializedName("py") val latitude: Double,
    @SerializedName("t") val previsionTime: String
): Serializable {
    override fun toString(): String {
        return "BusPosition(busCode='$busCode', latitude=$latitude, longitude=$longitude, previsionTime='$previsionTime')"
    }
}