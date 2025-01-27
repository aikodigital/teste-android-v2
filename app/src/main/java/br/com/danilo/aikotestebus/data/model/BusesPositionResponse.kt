package br.com.danilo.aikotestebus.data.model

import com.google.gson.annotations.SerializedName

data class BusesPositionResponse(
    @SerializedName("hr") val hourGenerated: String?,
    @SerializedName("l") val busList: List<BusesRelationResponse>?
)