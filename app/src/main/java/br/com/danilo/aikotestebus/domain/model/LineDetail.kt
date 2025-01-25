package br.com.danilo.aikotestebus.domain.model

data class LineDetail(
    val lineId: Int,
    val isCircular: Boolean,
    val firstLabelNumber: String,
    val secondLabelNumber: Int,
    val meaning: Int,
    val mainTerminal: String,
    val secondaryTerminal: String
)