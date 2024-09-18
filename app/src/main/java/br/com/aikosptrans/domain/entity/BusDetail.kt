package br.com.aikosptrans.domain.entity

data class BusDetail(
    val prefix: Int,
    val isAccessible: Boolean,
    val dateTime: String,
    val latitude: Double,
    val longitude: Double
)