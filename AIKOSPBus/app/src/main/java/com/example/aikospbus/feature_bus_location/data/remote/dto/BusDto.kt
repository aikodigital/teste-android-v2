package com.example.aikospbus.feature_bus_location.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BusDto(
    @SerializedName("hr")
    val horaConsulta: String,
    @SerializedName("vs")
    val vehicleDtos: List<VehicleDto>
)
