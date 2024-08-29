package com.example.viewtab.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Linha(
    @SerializedName("cl")
    val linhaCode: Long,

    @SerializedName("lc")
    val isCircular: Boolean,

    @SerializedName("lt")
    val fistNumLinha: String,

    @SerializedName("tl")
    val secundeNumLinha: Long,

    @SerializedName("sl")
    val sentido: Long,

    @SerializedName("tp")
    val namePrimaryBySecundary: String,

    @SerializedName("ts")
    val nameSecundaryByPrimary: String
): Serializable
