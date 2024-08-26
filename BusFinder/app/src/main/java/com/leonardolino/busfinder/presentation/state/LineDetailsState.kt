package com.leonardolino.busfinder.presentation.state

import com.leonardolino.busfinder.domain.model.LineInfo
import com.leonardolino.busfinder.domain.model.VehiclePosition

data class LineDetailsState(
    val lineDetails: UiState<List<LineInfo>> = UiState.Loading,
    val vehiclePositions: UiState<VehiclePosition> = UiState.Loading
)
