package br.com.aikosptrans.presentation.buslinedetail.viewmodel

import br.com.aikosptrans.domain.entity.BusLine
import br.com.aikosptrans.domain.entity.BusStop

data class BusLineDetailUiState(
    val line: BusLine? = null,
    val busStops: List<BusStop> = listOf(),
    val hasError: Boolean = false,
    val arriveForecastTime: List<String> = listOf(),
    val shouldShowTime: Boolean = false
)
