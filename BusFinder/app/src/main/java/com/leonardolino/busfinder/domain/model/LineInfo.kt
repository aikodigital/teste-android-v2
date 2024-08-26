package com.leonardolino.busfinder.domain.model

data class LineInfo(
    val code: Int,
    val isCircular: Boolean,
    val signPrefix: String,
    val lineDirection: Int,
    val lineSuffix: Int,
    val primarySign: String,
    val secondarySign: String
)