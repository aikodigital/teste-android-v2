package com.example.viewtab.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Parada(
    @SerializedName("cp")
    val paradaCode: Long,

    @SerializedName("np")
    val nomeParada: String,

    @SerializedName("ed")
    val endereço: String,

    @SerializedName("py")
    val latitude: Double,

    @SerializedName("px")
    val longitude: Double,

    ):
    Serializable
