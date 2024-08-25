package com.example.aikospbus.feature_bus_stops.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BusStopsDto(
    @SerializedName("cp")
    val codigoParada: Int,              // Código identificador da parada

    @SerializedName("np")
    val nomeParada: String,             // Nome da parada

    @SerializedName("ed")
    val enderecoParada: String,         // Endereço ou descrição da parada

    @SerializedName("py")
    val latitude: Double,               // Latitude da parada

    @SerializedName("px")
    val longitude: Double               // Longitude da parada
)

