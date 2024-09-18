package br.com.aikosptrans.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class ArrivalForecastResponse(
    @SerializedName("hr") val dateTime: String,
    @SerializedName("p") val busStop: ArrivalForecastStopResponse?
)

data class ArrivalForecastStopResponse(
    @SerializedName("cp") val idStop: Int,
    @SerializedName("np") val name: String,
    @SerializedName("py") val latitude: Double,
    @SerializedName("px") val longitude: Double,
    @SerializedName("l") val busLines: List<ArrivalForecastLineResponse>
)

data class ArrivalForecastLineResponse(
    @SerializedName("c") val number: String,
    @SerializedName("cl") val idLine: Int,
    @SerializedName("sl") val flow: Int,
    @SerializedName("vs") val buses: List<ArrivalForecastBusResponse>
)

data class ArrivalForecastBusResponse(
    @SerializedName("t") val arrivalForecastTime: String
)
