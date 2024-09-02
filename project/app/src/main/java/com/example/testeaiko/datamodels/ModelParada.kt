package com.example.testeaiko.datamodels

data class ModelParada(
        val cp: Long,              // Código de parada
        val np: String,            // Nome da parada
        val ed: String? = null,            // Endereço da parada
        val py: Double,            // Latitude da parada
        val px: Double,            // Longitude da parada
        val l: List<ModelLinha>? = null,  // Optional list of ModelVeiculo
        val vs: List<ModelLinha>? = null  // Optional list of ModelVeiculo
)
