package com.leonardolino.busfinder.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonardolino.busfinder.BuildConfig
import com.leonardolino.busfinder.domain.model.BusStop
import com.leonardolino.busfinder.domain.model.EstimatedArrival
import com.leonardolino.busfinder.domain.usecase.AuthenticateUseCase
import com.leonardolino.busfinder.domain.usecase.GetBusStopsUseCase
import com.leonardolino.busfinder.domain.usecase.GetNextArrivalsUseCase
import com.leonardolino.busfinder.presentation.state.UiState
import com.leonardolino.busfinder.presentation.viewmodel.MapViewModel.ErrorMessages.ERROR_AUTHENTICATE
import com.leonardolino.busfinder.presentation.viewmodel.MapViewModel.ErrorMessages.ERROR_BUS_STOPS
import com.leonardolino.busfinder.presentation.viewmodel.MapViewModel.ErrorMessages.ERROR_NEXT_ARRIVALS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okio.IOException
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val authenticateUseCase: AuthenticateUseCase,
    private val getBusStopsUseCase: GetBusStopsUseCase,
    private val getNextArrivalsUseCase: GetNextArrivalsUseCase
) : ViewModel() {

    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated.asStateFlow()

    private val _busStopsListState = MutableStateFlow<UiState<List<BusStop>>>(UiState.Loading)
    val busStopsListState: StateFlow<UiState<List<BusStop>>> = _busStopsListState.asStateFlow()

    private val _arrivalsState = MutableStateFlow<UiState<EstimatedArrival>>(UiState.Loading)
    val arrivalsState: StateFlow<UiState<EstimatedArrival>> = _arrivalsState.asStateFlow()

    private val _isSheetVisible = MutableStateFlow(false)
    val isSheetVisible: StateFlow<Boolean> = _isSheetVisible.asStateFlow()

    init {
        viewModelScope.launch {
            authenticate()
            fetchBusStops("")
        }
    }

    private suspend fun authenticate() {
        try {
            val data = authenticateUseCase(BuildConfig.OLHO_VIVO_API_KEY)
            _isAuthenticated.value = data
        } catch (e: IOException) {
            Log.e("authenticate", ERROR_AUTHENTICATE)
        }
    }

    private suspend fun fetchBusStops(terms: String) {
        _busStopsListState.update { UiState.Loading }
        try {
            val data = getBusStopsUseCase(terms)
            _busStopsListState.update { UiState.Success(data) }
        } catch (e: IOException) {
            _arrivalsState.update { UiState.Error(ERROR_BUS_STOPS) }
        }
    }

    fun onInfoWindowClick(stopCode: Int) {
        fetchNextArrivals(stopCode)
    }

    fun hideBottomSheet() {
        _isSheetVisible.value = false
    }

    private fun fetchNextArrivals(stopCode: Int) {
        viewModelScope.launch {
            _isSheetVisible.value = true
            _arrivalsState.update { UiState.Loading }
            try {
                val data = getNextArrivalsUseCase(stopCode)
                _arrivalsState.update { UiState.Success(data) }
            } catch (e: IOException) {
                _arrivalsState.update { UiState.Error(ERROR_NEXT_ARRIVALS) }
            }
        }
    }

    private object ErrorMessages {
        const val ERROR_AUTHENTICATE = "Failed to authenticate"
        const val ERROR_BUS_STOPS = "Failed to load bus stops"
        const val ERROR_NEXT_ARRIVALS = "Failed to load next arrivals"
    }
}