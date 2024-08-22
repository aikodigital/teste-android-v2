package com.aiko.teste.sptrans.data.objects

import com.google.gson.annotations.SerializedName

data class BusPositions(
    @SerializedName("hr") val hour: String,
    @SerializedName("l") val lines: List<Line>
) {
    override fun toString(): String {
        return "BusPositions(hour='$hour', lines=$lines)"
    }
}