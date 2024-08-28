package com.example.viewtab.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Previsao(

    @SerializedName("hr")
    val hora: String,

    @SerializedName("p")
    val p: ParadaPrevisao,
): Serializable

