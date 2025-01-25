package br.com.danilo.aikotestebus.data.model

import com.google.gson.annotations.SerializedName

data class LineDetailResponse(
    @SerializedName("cl") val lineId: Int?,
    @SerializedName("lc") val isCircular: Boolean?,
    @SerializedName("lt") val firstLabelNumber: String?,
    @SerializedName("tl") val secondLabelNumber: Int?,
    @SerializedName("sl") val meaning: Int?,
    @SerializedName("tp") val mainTerminal: String?,
    @SerializedName("ts") val secondaryTerminal: String?
)
