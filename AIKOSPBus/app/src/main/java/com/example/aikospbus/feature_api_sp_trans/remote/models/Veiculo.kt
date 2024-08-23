package com.example.aikospbus.feature_api_sp_trans.remote.models

import com.google.gson.annotations.SerializedName

data class Veiculo(
    @SerializedName("p")
    val prefixo: Int,                    // Prefixo do veículo

    @SerializedName("a")
    val acessibilidade: Boolean,         // Se o veículo é acessível

    @SerializedName("ta")
    val horarioAmostra: String,          // Horário da última amostra

    @SerializedName("py")
    val latitude: Double,                // Latitude do veículo

    @SerializedName("px")
    val longitude: Double,               // Longitude do veículo

    @SerializedName("op")
    val operador: String                 // Nome do operador do veículo
)

