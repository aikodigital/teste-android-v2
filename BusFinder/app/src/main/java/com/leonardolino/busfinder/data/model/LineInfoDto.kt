package com.leonardolino.busfinder.data.model


import com.google.gson.annotations.SerializedName
import com.leonardolino.busfinder.domain.model.LineInfo

data class LineInfoDto(
    @SerializedName("cl") val code: Int,
    @SerializedName("lc") val isCircular: Boolean,
    @SerializedName("lt") val signPrefix: String,
    @SerializedName("sl") val lineDirection: Int,
    @SerializedName("tl") val lineSuffix: Int,
    @SerializedName("tp") val primarySign: String,
    @SerializedName("ts") val secondarySign: String
) {
    fun toLineInfo() = LineInfo(
        code, isCircular, signPrefix, lineDirection, lineSuffix, primarySign, secondarySign
    )
}