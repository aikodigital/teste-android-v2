package com.example.carlosluzsaikotest.data.model

data class Linha(
    val c: String,          // Letreiro completo
    val cl: Int,            // Código identificador da linha
    val sl: Int,            // Sentido de operação
    val lt0: String,        // Letreiro de destino
    val lt1: String,        // Letreiro de origem
    val qv: Int,            // Quantidade de veículos localizados
    val vs: List<Veiculo>   // Relação de veículos localizados
)
