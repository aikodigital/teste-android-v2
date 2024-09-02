package com.example.testeaiko.datamodels

data class ModelPosicao(
        val hr: String,          // Horário de referência da geração das informações
        val l: List<ModelLinha>       // Relação de linhas localizadas
)