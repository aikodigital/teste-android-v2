package br.com.aikosptrans.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class BusStopResponse(
    @SerializedName("cp") val busStopId: Int,
    @SerializedName("np") val name: String,
    @SerializedName("ed") val address: String,
    @SerializedName("py") val latitude: Double,
    @SerializedName("px") val longitude: Double
)
