package br.com.aiko.projetoolhovivo.data.model.position

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PositionByListLine(
    @JsonProperty("hr")
    val lastUpdate: String,
    @JsonProperty("l")
    val lines: List<PositionLine>
)