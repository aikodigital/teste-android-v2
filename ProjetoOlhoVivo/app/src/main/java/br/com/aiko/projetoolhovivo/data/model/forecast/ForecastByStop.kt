package br.com.aiko.projetoolhovivo.data.model.forecast

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ForecastByStop(
    @JsonProperty("hr")
    val lastUpdate: String,
    @JsonProperty("p")
    val stop: ForecastStopByLine
)