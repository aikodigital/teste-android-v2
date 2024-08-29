package br.com.aiko.projetoolhovivo.data.model.forecast

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ForecastVehicle (
    @JsonProperty("t")
    val forecast: String,
    @JsonProperty("p")
    val prefixVehicle: Int
)