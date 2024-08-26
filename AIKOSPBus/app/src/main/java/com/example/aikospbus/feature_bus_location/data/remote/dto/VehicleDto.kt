package com.example.aikospbus.feature_bus_location.data.remote.dto

import com.google.gson.annotations.SerializedName

data class VehicleDto(
    @SerializedName("p")
    val prefixo: Int,

    @SerializedName("a")
    val acessibilidade: Boolean,

    @SerializedName("ta")
    val horarioAmostra: String,

    @SerializedName("py")
    val latitude: Double,

    @SerializedName("px")
    val longitude: Double,

    @SerializedName("op")
    val operador: String
)

