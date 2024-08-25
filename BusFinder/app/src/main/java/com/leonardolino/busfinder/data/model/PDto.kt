package com.leonardolino.busfinder.data.model

import com.leonardolino.busfinder.domain.model.P

data class PDto(
    val cp: Int,
    val np: String,
    val py: Double,
    val px: Double,
    val l: List<LDto>
) {
    fun toP() = P(cp, np, py, px, l.map { it.toL() })
}
