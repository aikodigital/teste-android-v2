package br.com.danilo.aikotestebus.data.model

import com.google.gson.annotations.SerializedName

data class ArrivalForecastResponse(
    @SerializedName("hr") val dateTime: String?,
    @SerializedName("p") val busStop: ArrivalForecastStopResponse?
)

data class ArrivalForecastStopResponse(
    @SerializedName("cp") val idStop: Int?,
    @SerializedName("np") val stopName: String?,
    @SerializedName("py") val latitude: Double?,
    @SerializedName("px") val longitude: Double?,
    @SerializedName("l") val busList: List<ArrivalForecastRelationResponse>?
)

data class ArrivalForecastRelationResponse(
    @SerializedName("c") val letterComplete: String?,
    @SerializedName("cl") val idLine: Int?,
    @SerializedName("lt0") val lineDestination: String?,
    @SerializedName("lt1") val lineOrigin: String?,
    @SerializedName("sl") val flow: Int?,
    @SerializedName("vs") val buses: List<ArrivalForecastBusResponse>?
)

data class ArrivalForecastBusResponse(
    @SerializedName("p") val prefixNumber: Int?,
    @SerializedName("a") val isAccessible: Boolean?,
    @SerializedName("ta") val dateTime: String?,
    @SerializedName("py") val latitude: Double?,
    @SerializedName("px") val longitude: Double?,
    @SerializedName("t") val arrivalForecastTime: String?
)
