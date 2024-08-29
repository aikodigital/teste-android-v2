package com.example.viewtab.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Veiculo(
    @SerializedName("p")
    val prefix: String,

    @SerializedName("t")
    val previsaoHorario: String,

    @SerializedName("a")
    val isDeficiente: Boolean,

    @SerializedName("ta")
    val horaCaptura: String,

    @SerializedName("py")
    val latitude: Double,

    @SerializedName("px")
    val longitude: Double,
): Serializable
