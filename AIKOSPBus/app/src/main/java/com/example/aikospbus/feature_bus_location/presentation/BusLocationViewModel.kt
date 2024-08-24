package com.example.aikospbus.feature_bus_location.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aikospbus.feature_bus_location.domain.model.BusLocation
import com.example.aikospbus.feature_bus_location.domain.use_case.BusLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusLocationViewModel @Inject constructor(
    private val busLocationUseCase: BusLocationUseCase
): ViewModel() {


    fun insertBusLocation(busLocation: BusLocation) = viewModelScope.launch {
        busLocationUseCase(busLocation)
    }
}