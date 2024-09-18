package br.com.aikosptrans.presentation.busmap.viewmodel

import br.com.aikosptrans.domain.entity.ClusterData

data class BusMapUiState(
    val buses: List<ClusterData> = listOf()
)
