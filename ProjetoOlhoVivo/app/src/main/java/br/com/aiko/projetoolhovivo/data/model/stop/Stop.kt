package br.com.aiko.projetoolhovivo.data.model.stop

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Stop(
    @JsonProperty("cp")
    val code: Int,
    @JsonProperty("np")
    val name: String,
    @JsonProperty("py")
    val latitude: Double,
    @JsonProperty("px")
    val longitude: Double
)