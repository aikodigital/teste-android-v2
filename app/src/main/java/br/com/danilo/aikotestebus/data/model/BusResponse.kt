package br.com.danilo.aikotestebus.data.model

import com.google.gson.annotations.SerializedName

data class BusResponse(
    @SerializedName("p") val prefixNumber: Int?,
    @SerializedName("a") val isAccessible: Boolean?,
    @SerializedName("ta") val dateTime: String?,
    @SerializedName("py") val latitude: Double?,
    @SerializedName("px") val longitude: Double?
)