package br.com.aikosptrans.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class BusLineDetailResponse(
    @SerializedName("cl") val lineId: Int,
    @SerializedName("lc") val isCircular: Boolean,
    @SerializedName("lt") val firstNumber: String,
    @SerializedName("tl") val secondNumber: Int,
    @SerializedName("sl") val flow: Int,
    @SerializedName("tp") val mainTerminalName: String,
    @SerializedName("ts") val secondaryTerminalName: String
)
