package com.leonardolino.busfinder.presentation.state

import com.leonardolino.busfinder.domain.model.NextArrivals

data class ArrivalsSheetUiState(
    val isVisible: Boolean = false,
    val contentState: UiState<NextArrivals> = UiState.Loading
)
