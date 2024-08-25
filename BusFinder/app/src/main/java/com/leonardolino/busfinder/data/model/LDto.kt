package com.leonardolino.busfinder.data.model

import com.leonardolino.busfinder.domain.model.L

data class LDto(
    val c: String,
    val cl: Int,
    val sl: Int,
    val lt0: String,
    val lt1: String,
    val qv: Int,
    val vs: List<VsDto>
) {
    fun toL() = L(c, cl, sl, lt0, lt1, qv, vs.map { it.toVs() })
}
