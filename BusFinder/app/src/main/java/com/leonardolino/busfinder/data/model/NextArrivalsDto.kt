package com.leonardolino.busfinder.data.model

import com.leonardolino.busfinder.domain.model.NextArrivals

data class NextArrivalsDto(
    val hr: String,
    val p: PDto?
) {
    fun toNextArrivals() = NextArrivals(hr, p?.toP())
}
