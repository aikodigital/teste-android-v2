package br.com.aiko.projetoolhovivo.data.model.position

import br.com.aiko.projetoolhovivo.data.model.vehicle.Vehicle
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class PositionByVehicle(
    @JsonProperty("hr")
    val lastUpdate: String,
    @JsonProperty("vs")
    val vehicles: List<Vehicle>
)