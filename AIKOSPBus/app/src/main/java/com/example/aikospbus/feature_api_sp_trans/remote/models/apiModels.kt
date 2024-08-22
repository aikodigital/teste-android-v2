package com.example.aikospbus.feature_api_sp_trans.remote.models

class apiModels {

    data class VehiclePositionResponse(
        val retorno: Retorno
    )

    data class Retorno(
        val codigoLinha: String,
        val posicoes: List<Posicao>
    )

    data class Posicao(
        val codigoVeiculo: String,
        val latitude: Double,
        val longitude: Double,
        val hora: String
    )

}