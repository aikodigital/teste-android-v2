package com.example.aikospbus.feature_bus_corridor.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BusCorridorDto(
    @SerializedName("cc")
    val codigoCorredor: Int,

    @SerializedName("nc")
    val nomeCorredor: String
)

