package com.example.aikospbus.feature_api_sp_trans.remote.models

import com.google.gson.annotations.SerializedName

data class Corredor(
    @SerializedName("cc")
    val codigoCorredor: Int,         // Código identificador do corredor

    @SerializedName("nc")
    val nomeCorredor: String         // Nome ou descrição do corredor
)

