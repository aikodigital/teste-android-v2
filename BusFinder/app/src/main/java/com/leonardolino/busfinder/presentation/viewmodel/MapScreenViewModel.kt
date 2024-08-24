package com.leonardolino.busfinder.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonardolino.busfinder.BuildConfig
import com.leonardolino.busfinder.domain.model.BusStop
import com.leonardolino.busfinder.domain.usecase.AuthenticateUseCase
import com.leonardolino.busfinder.domain.usecase.GetBusStopsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val authenticateUseCase: AuthenticateUseCase,
    private val getBusStopsUseCase: GetBusStopsUseCase,
) : ViewModel() {

    private val _busStopsData = MutableStateFlow<List<BusStop>>(emptyList())
    val busStopsData: StateFlow<List<BusStop>> = _busStopsData

    private val _isAuthenticated = mutableStateOf(false)
    val isAuthenticated: State<Boolean> = _isAuthenticated

    init {
        authenticate()
    }

    fun authenticate() {
        viewModelScope.launch {
            _isAuthenticated.value = authenticateUseCase(BuildConfig.OLHO_VIVO_API_KEY)
        }
    }

    fun fetchBusStops() {
        if (isAuthenticated.value) {
            viewModelScope.launch {
                _busStopsData.value = getBusStopsUseCase("")
            }
        }
    }
}