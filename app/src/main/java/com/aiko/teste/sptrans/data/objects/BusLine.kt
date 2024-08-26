package com.aiko.teste.sptrans.data.objects

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BusLine(
    @SerializedName("c") val code: String = "",
    @SerializedName("cl") val lineCode: String = "",
    @SerializedName("lt0") val lineStart: String = "",
    @SerializedName("lt1") val lineEnd: String = "",
    @SerializedName("tp") val mainTerminal: String = "",
    @SerializedName("ts") val secondaryTerminal: String = "",
    @SerializedName("vs") val positions: List<BusPosition> = listOf(),
    @SerializedName("sl") val lineWay: Int = 0
): Serializable {
    override fun toString(): String {
        return "BusLine(code='$code', lineCode='$lineCode', lineStart='$lineStart', lineEnd='$lineEnd', mainTerminal='$mainTerminal', secondaryTerminal='$secondaryTerminal', positions=$positions, lineWay=$lineWay)"
    }
}

data class BusPosition(
    @SerializedName("p") val busCode: String = "",
    @SerializedName("px") val longitude: Double = 0.0,
    @SerializedName("py") val latitude: Double = 0.0,
    @SerializedName("t") val previsionTime: String = ""
): Serializable {
    override fun toString(): String {
        return "BusPosition(busCode='$busCode', latitude=$latitude, longitude=$longitude, previsionTime='$previsionTime')"
    }
}