package com.example.aikospbus.feature_api_sp_trans.remote.models

import com.google.gson.annotations.SerializedName

data class Bus(
    @SerializedName("hr")
    val horaConsulta: String,            // Horário da consulta da posição

    @SerializedName("vs")
    val veiculos: List<Veiculo>          // Lista de veículos na linha
)
