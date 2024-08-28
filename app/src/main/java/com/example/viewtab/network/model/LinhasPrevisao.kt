package com.example.viewtab.network.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LinhasPrevisao(

    @SerializedName("c")
    val letriroCompleto: String,

    @SerializedName("cl")
    val linhaCode: Long,

    @SerializedName("sl")
    val sentido: Long,

    @SerializedName("lt0")
    val namePrimaryBySecundary: String,

    @SerializedName("lt1")
    val nameSecundaryByPrimary: String,

    @SerializedName("qv")
    val quantidadeVeiculos: Long,

    @SerializedName("vs")
    val veiculosList: List<Veiculo>,

    @SerializedName("lc")
    val isCircular: Boolean,

    ): Serializable


