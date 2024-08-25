package com.example.aikospbus.feature_bus_location.data.remote.dto

import com.example.aikospbus.feature_bus_location.domain.model.Veiculo
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class BusDto(
    @SerializedName("hr")
    val horaConsulta: String,
    @SerializedName("vs")
    val veiculos: List<Veiculo>
)
