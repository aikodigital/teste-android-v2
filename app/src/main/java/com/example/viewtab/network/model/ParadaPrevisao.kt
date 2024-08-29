package com.example.viewtab.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ParadaPrevisao(

    @SerializedName("l")
    val linhas: List<LinhasPrevisao>,

    @SerializedName("cp")
    val paradaCode: Long,

    @SerializedName("np")
    val nomeParada: String,

    @SerializedName("py")
    val latitude: Double,

    @SerializedName("px")
    val longitude: Double,

    ): Serializable

