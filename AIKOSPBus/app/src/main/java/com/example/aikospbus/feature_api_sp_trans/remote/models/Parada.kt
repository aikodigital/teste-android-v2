package com.example.aikospbus.feature_api_sp_trans.remote.models

import com.google.gson.annotations.SerializedName

data class Parada(
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

