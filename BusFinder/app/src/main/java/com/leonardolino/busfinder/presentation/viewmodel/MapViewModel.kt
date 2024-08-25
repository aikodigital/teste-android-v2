package com.leonardolino.busfinder.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonardolino.busfinder.BuildConfig
import com.leonardolino.busfinder.domain.model.BusStop
import com.leonardolino.busfinder.domain.model.NextArrivals
import com.leonardolino.busfinder.domain.usecase.AuthenticateUseCase
import com.leonardolino.busfinder.domain.usecase.GetBusStopsUseCase
import com.leonardolino.busfinder.domain.usecase.GetNextArrivalsUseCase
import com.leonardolino.busfinder.presentation.state.ArrivalsSheetUiState
import com.leonardolino.busfinder.presentation.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

    private val _arrivalsState = MutableStateFlow<UiState<NextArrivals>>(UiState.Loading)
    val arrivalsState: StateFlow<UiState<NextArrivals>> = _arrivalsState.asStateFlow()

    private val _isSheetVisible = MutableStateFlow(false)
    val isSheetVisible: StateFlow<Boolean> = _isSheetVisible.asStateFlow()

    init {
        viewModelScope.launch {
            authenticate()
            loadBusStops("")
        }
    }

    private suspend fun authenticate() {
        try {
            val data = authenticateUseCase(BuildConfig.OLHO_VIVO_API_KEY)
            _isAuthenticated.value = data
        } catch (e: Exception) {
            Log.e("authenticate", "Failed to authenticate")
        }
    }

    private suspend fun loadBusStops(terms: String) {
        _busStopsListState.update { UiState.Loading }
        try {
            val data = getBusStopsUseCase(terms)
            _busStopsListState.update { UiState.Success(data) }
        } catch (e: Exception) {
            _arrivalsState.update { UiState.Error("Failed to load bus stops") }
        }
    }

    fun onInfoWindowClick(stopCode: Int) {
        loadBottomSheetData(stopCode)
    }

    fun hideBottomSheet() {
        _isSheetVisible.value = false
    }

    private fun loadBottomSheetData(stopCode: Int) {
        viewModelScope.launch {
            _isSheetVisible.value = true
            _arrivalsState.update { UiState.Loading }
            try {
                val data = getNextArrivalsUseCase(stopCode)
                _arrivalsState.update { UiState.Success(data) }
            } catch (e: Exception) {
                _arrivalsState.update { UiState.Error("Failed to load next arrivals") }
            }
        }
    }
}