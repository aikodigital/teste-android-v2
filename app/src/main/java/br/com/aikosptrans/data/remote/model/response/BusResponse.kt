package br.com.aikosptrans.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class BusResponse(
    @SerializedName("p") val prefix: Int,
    @SerializedName("a") val isAccessible: Boolean,
    @SerializedName("ta") val dateTime: String,
    @SerializedName("py") val latitude: Double,
    @SerializedName("px") val longitude: Double
)
