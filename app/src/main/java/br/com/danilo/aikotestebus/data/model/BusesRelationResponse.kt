package br.com.danilo.aikotestebus.data.model

import com.google.gson.annotations.SerializedName

data class BusesRelationResponse(
    @SerializedName("c") val fullNumber: String?,
    @SerializedName("cl") val lineId: Int?,
    @SerializedName("sl") val address: Int?,
    @SerializedName("lt0") val destination: String?,
    @SerializedName("lt1") val origin: String?,
    @SerializedName("qv") val busQuantity: Int?,
    @SerializedName("vs") val buses: List<BusResponse>?
)