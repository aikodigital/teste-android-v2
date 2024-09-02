package com.example.testeaiko.datamodels

data class ModelLinha(
        val c: String? = null,   // Letreiro completo
        val cl: Int,     // Código identificador da linha. Este é um código identificador único de cada linha do sistema (por sentido de operação)
        val lt: String? = null, //  Informa a primeira parte do letreiro numérico da linha
        val lc: Boolean? = null,// Indica se uma linha opera no modo circular (sem um terminal secundário)
        val sl: Int,     // Sentido de operação (1: Terminal Principal -> Terminal Secundário, 2: Terminal Secundário -> Terminal Principal)
        val tl: Int? = null,/*Informa a segunda parte do letreiro numérico da linha, que indica se a linha opera nos modos:
BASE (10), ATENDIMENTO (21, 23, 32, 41)*/
        val lt0: String? = null, // Letreiro de destino da linha
        val lt1: String? = null, // Letreiro de origem da linha
        val tp: String? = null, // Informa o letreiro descritivo da linha no sentido Terminal Principal para Terminal Secundário
        val ts: String? = null, // Informa o letreiro descritivo da linha no sentido Terminal Secundário para Terminal Principal
        val qv: Int,     // Quantidade de veículos localizados
        val vs: List<ModelVeiculo> // Relação de veículos localizados
)
