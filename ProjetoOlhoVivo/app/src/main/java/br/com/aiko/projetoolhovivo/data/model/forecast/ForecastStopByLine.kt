package br.com.aiko.projetoolhovivo.data.model.forecast

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ForecastStopByLine(
    @JsonProperty("l")
    val lines: List<ForecastLine>
)