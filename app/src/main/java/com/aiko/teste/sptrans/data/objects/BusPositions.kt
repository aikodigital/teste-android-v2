package com.aiko.teste.sptrans.data.objects

import com.google.gson.annotations.SerializedName

data class BusPositions(
    @SerializedName("hr") val hour: String,
    @SerializedName("l") val lines: List<Bus>
) {
    override fun toString(): String {
        return "BusPositions(hour='$hour', lines=$lines)"
    }
}

data class Bus(
    @SerializedName("c") val code: String,
    @SerializedName("cl") val lineCode: String,
    @SerializedName("lt0") val lineStart: String,
    @SerializedName("lt1") val lineEnd: String,
    @SerializedName("vs") val positions: List<Position>
) {
    override fun toString(): String {
        return "Bus(code='$code', lineCode='$lineCode', lineStart='$lineStart', lineEnd='$lineEnd', positions=$positions)"
    }
}

data class Position(
    @SerializedName("px") val latitude: Double,
    @SerializedName("py") val longitude: Double
) {
    override fun toString(): String {
        return "Position(latitude=$latitude, longitude=$longitude)"
    }
}