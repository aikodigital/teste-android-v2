package com.leonardolino.busfinder.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonardolino.busfinder.domain.model.LineInfo
import com.leonardolino.busfinder.domain.model.VehiclePosition
import com.leonardolino.busfinder.domain.usecase.GetLineInfoUseCase
import com.leonardolino.busfinder.domain.usecase.GetVehiclesPositionUseCase
import com.leonardolino.busfinder.presentation.state.LineDetailsState
import com.leonardolino.busfinder.presentation.state.UiState
import com.leonardolino.busfinder.presentation.viewmodel.LineDetailsViewModel.ErrorMessages.ERROR_LINE_DETAILS
import com.leonardolino.busfinder.presentation.viewmodel.LineDetailsViewModel.ErrorMessages.ERROR_VEHICLE_POSITIONS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

@HiltViewModel
class LineDetailsViewModel @Inject constructor(
    private val getLineInfoUseCase: GetLineInfoUseCase,
    private val getVehiclePositionsUseCase: GetVehiclesPositionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LineDetailsState())
    val state: StateFlow<LineDetailsState> = _state.asStateFlow()

    private val _lineInfoState = MutableStateFlow<UiState<List<LineInfo>>>(UiState.Loading)
    val lineInfoState: StateFlow<UiState<List<LineInfo>>> = _lineInfoState.asStateFlow()

    private val _vehiclePositionsState = MutableStateFlow<UiState<VehiclePosition>>(UiState.Loading)
    val vehiclePositionsState: StateFlow<UiState<VehiclePosition>> =
        _vehiclePositionsState.asStateFlow()

    fun loadLineDetails(lineCode: Int, lineDirection: Int, fullSign: String) {
        viewModelScope.launch {
            _state.value = LineDetailsState(
                lineDetails = UiState.Loading, vehiclePositions = UiState.Loading
            )

            try {
                val lineDetails = getLineInfoUseCase(fullSign, lineDirection)
                _state.value = _state.value.copy(
                    lineDetails = UiState.Success(lineDetails)
                )
            } catch (e: IOException) {
                _state.value = _state.value.copy(
                    lineDetails = UiState.Error(ERROR_LINE_DETAILS)
                )
            }

            try {
                val vehiclePositions = getVehiclePositionsUseCase(lineCode)
                _state.value = _state.value.copy(
                    vehiclePositions = UiState.Success(vehiclePositions)
                )
            } catch (e: IOException) {
                _state.value = _state.value.copy(
                    vehiclePositions = UiState.Error(ERROR_VEHICLE_POSITIONS)
                )
            }
        }
    }

    fun clearState() {
        _state.value = LineDetailsState()
    }

    private object ErrorMessages {
        const val ERROR_LINE_DETAILS = "Failed to load line details"
        const val ERROR_VEHICLE_POSITIONS = "Failed to load vehicle positions"
    }
}