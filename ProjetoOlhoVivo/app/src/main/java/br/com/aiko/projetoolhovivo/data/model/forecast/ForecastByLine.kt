package br.com.aiko.projetoolhovivo.data.model.forecast

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ForecastByLine(
    @JsonProperty("hr")
    val lastUpdate: String,
    @JsonProperty("ps")
    val stops: List<ForecastLineByStop>
)