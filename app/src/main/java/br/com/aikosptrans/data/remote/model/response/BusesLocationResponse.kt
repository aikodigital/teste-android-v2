package br.com.aikosptrans.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class BusesLocationResponse(
    @SerializedName("hr") val hourGenerated: String,
    @SerializedName("l") val busLine: List<BusLineResponse>
)