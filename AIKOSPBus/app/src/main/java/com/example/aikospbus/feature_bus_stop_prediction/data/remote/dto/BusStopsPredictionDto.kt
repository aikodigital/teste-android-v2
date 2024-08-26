package com.example.aikospbus.feature_bus_stop_prediction.data.remote.dto


import com.google.gson.annotations.SerializedName

data class BusStopPredictionDto(
    @SerializedName("hr")
    val horaPrevisao: String,

    @SerializedName("p")
    val parada: ParadaTestDto
)

data class ParadaTestDto(
    @SerializedName("cp")
    val codigoParada: Int,

    @SerializedName("np")
    val nomeParada: String,

    @SerializedName("py")
    val latitudeParada: Double,

    @SerializedName("px")
    val longitudeParada: Double,

    @SerializedName("l")
    val linhas: List<BusLinesTestDto>
)

data class BusLinesTestDto(
    @SerializedName("c")
    val codigoLinha: String,

    @SerializedName("cl")
    val codigoLinhaCompleto: Int,

    @SerializedName("sl")
    val sentidoLinha: Int,

    @SerializedName("lt0")
    val origem: String,

    @SerializedName("lt1")
    val destino: String,

    @SerializedName("qv")
    val quantidadeVeiculos: Int,

    @SerializedName("vs")
    val veiculos: List<VeiculosTestDto>
)

data class VeiculosTestDto(
    @SerializedName("p")
    val prefixo: String,

    @SerializedName("t")
    val horarioChegada: String,

    @SerializedName("a")
    val acessibilidade: Boolean,

    @SerializedName("ta")
    val horarioAtualizacao: String,

    @SerializedName("py")
    val latitude: Double,

    @SerializedName("px")
    val longitude: Double
)


