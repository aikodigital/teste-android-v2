package com.example.aikospbus.feature_api_sp_trans.remote.models

import com.google.gson.annotations.SerializedName

data class Line(
    @SerializedName("cl")
    val codigoLinha: Int,

    @SerializedName("lc")
    val circular: Boolean,

    @SerializedName("lt")
    val letreiro: String,

    @SerializedName("sl")
    val sentido: Int,

    @SerializedName("tl")
    val tipo: Int,

    @SerializedName("tp")
    val terminalPrincipal: String,

    @SerializedName("ts")
    val terminalSecundario: String
)
