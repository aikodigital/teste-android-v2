package br.com.aiko.projetoolhovivo.data.model.line

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Line(
    @JsonProperty("lt")
    val firstSign: String,
    @JsonProperty("tl")
    val secondSign: Int,
    @JsonProperty("sl")
    val direction: Int,
    @JsonProperty("tp")
    val terminalPrimaryBySecondary: String,
    @JsonProperty("ts")
    val terminalSecondaryByPrimary: String
)