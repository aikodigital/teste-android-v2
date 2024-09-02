package com.example.testeaiko.datamodels

data class ModelPrevisaoParada(
        val hr: String,  // Horário da previsão
        val p: ModelParada // Relação de linhas
)