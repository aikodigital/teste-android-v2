package br.com.aikosptrans.presentation.busstopmap.viewmodel

import br.com.aikosptrans.domain.entity.ClusterData

data class BusStopMapUiState(
    val busStops: List<ClusterData> = listOf(),
    val query: String = ""
)
