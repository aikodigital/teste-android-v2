package br.com.aiko.projetoolhovivo.data.model.position

import br.com.aiko.projetoolhovivo.data.model.vehicle.Vehicle
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PositionLine(
    @JsonProperty("c")
    val sign: String,
    @JsonProperty("cl")
    val code: Int,
    @JsonProperty("vs")
    val vehicles: List<Vehicle>
)