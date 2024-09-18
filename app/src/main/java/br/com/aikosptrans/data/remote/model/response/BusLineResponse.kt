package br.com.aikosptrans.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class BusLineResponse(
    @SerializedName("c") val fullNumber: String,
    @SerializedName("cl") val lineId: Int,
    @SerializedName("sl") val flow: Int,
    @SerializedName("lt0") val destination: String,
    @SerializedName("lt1") val origin: String,
    @SerializedName("qv") val busQuantity: Int,
    @SerializedName("vs") val buses: List<BusResponse>
)
