package br.com.aikosptrans.domain.entity

sealed class CityLocation(
    val latitude: Double,
    val longitude: Double
) {
    data object SAO_PAULO: CityLocation(
        latitude = -23.551740,
        longitude = -46.634859
    )
}
