package com.example.viewtab.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Posicao(

    @SerializedName("hr")
    val hora: String,

    @SerializedName("l")
    val linha: List<LinhasPrevisao>,
): Serializable

