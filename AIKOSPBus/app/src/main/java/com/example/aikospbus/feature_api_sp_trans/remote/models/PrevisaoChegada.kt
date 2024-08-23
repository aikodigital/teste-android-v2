package com.example.aikospbus.feature_api_sp_trans.remote.models

import com.google.gson.annotations.SerializedName

data class PrevisaoChegada(
    @SerializedName("hr")
    val horarioPrevisto: String,               // Código identificador da linha de ônibus

    @SerializedName("p")
    val codigoLinha: Int        // Horário previsto para a chegada do ônibus
)

