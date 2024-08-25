package com.example.aikospbus.feature_bus_location.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aikospbus.feature_bus_location.data.remote.dto.BusDto
import com.example.aikospbus.feature_bus_location.domain.model.BusLocationModel
import com.example.aikospbus.feature_bus_location.domain.use_case.BusLocationUseCase
import com.example.aikospbus.feature_bus_location.domain.use_case.GetRemoteBusLocationDataUseCase
import com.example.aikospbus.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusLocationViewModel @Inject constructor(
    private val busLocationUseCase: BusLocationUseCase,
    private val getRemoteBusLocationDataUseCase: GetRemoteBusLocationDataUseCase
): ViewModel() {

    private val _busDtoLocationDataModel = MutableLiveData<BusLocationModel?>()

    val busDtoLocationDataModel: MutableLiveData<BusLocationModel?>
        get() = _busDtoLocationDataModel


    fun insertBusLocation(busLocationModel: BusLocationModel) = viewModelScope.launch {
        busLocationUseCase(busLocationModel)
    }

    fun getRemoteBusLocationData(cookie: String, lineCode: Int) = viewModelScope.launch {
        getRemoteBusLocationDataUseCase(cookie, lineCode).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _busDtoLocationDataModel.value = result.data
                    println("RESULTADO DA API: ${result.data?.veiculos}")
                    println("API SUCCESS")
                }
                is Resource.Error -> {
                    _busDtoLocationDataModel.value = result.data
                    println("API ERROR")
                }
                is Resource.Loading -> {
                    _busDtoLocationDataModel.value = result.data
                    println("API LOADING")
                }
            }
        }.launchIn(this)
    }
}