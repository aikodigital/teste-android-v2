package br.com.aiko.projetoolhovivo.data.model.vehicle

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Vehicle(
    @JsonProperty("p")
    val prefixVehicle: Int,
    @JsonProperty("py")
    val latitude: Double,
    @JsonProperty("px")
    val longitude: Double
)