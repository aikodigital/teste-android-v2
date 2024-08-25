package com.leonardolino.busfinder.data.model

import com.leonardolino.busfinder.domain.model.Vs

data class VsDto(
    val p: String,
    val t: String,
    val a: Boolean,
    val ta: String,
    val py: Double,
    val px: Double
) {
    fun toVs() = Vs(p, t, a, ta, py, px)
}
