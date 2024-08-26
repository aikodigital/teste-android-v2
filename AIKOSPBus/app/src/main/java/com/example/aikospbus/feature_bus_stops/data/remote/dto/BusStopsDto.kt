package com.example.aikospbus.feature_bus_stops.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BusStopsDto(
    @SerializedName("cp")
    val codigoParada: Int,

    @SerializedName("np")
    val nomeParada: String,

    @SerializedName("ed")
    val enderecoParada: String,

    @SerializedName("py")
    val latitude: Double,

    @SerializedName("px")
    val longitude: Double
)

