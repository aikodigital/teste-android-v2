package br.com.aikosptrans.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class BusLine(
    val fullNumber: String,
    val lineId: Int,
    val flow: Int,
    val destination: String,
    val origin: String,
    val isCircular: Boolean? = null,
)