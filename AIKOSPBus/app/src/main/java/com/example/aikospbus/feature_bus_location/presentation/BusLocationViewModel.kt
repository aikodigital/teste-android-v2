package com.example.aikospbus.feature_bus_location.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aikospbus.feature_bus_location.domain.model.BusLocationModel
import com.example.aikospbus.feature_bus_location.domain.use_case.InsertBusLocationUseCase
import com.example.aikospbus.feature_bus_location.domain.use_case.GetRemoteBusLocationDataUseCase
import com.example.aikospbus.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusLocationViewModel @Inject constructor(
    private val insertBusLocationUseCase: InsertBusLocationUseCase,
    private val getRemoteBusLocationDataUseCase: GetRemoteBusLocationDataUseCase
): ViewModel() {

    private val _busDtoLocationDataModel = MutableLiveData<BusLocationModel?>()
    val busDtoLocationDataModel: MutableLiveData<BusLocationModel?>
        get() = _busDtoLocationDataModel

    private val _hasVehicleData = MutableLiveData<String>()
    val hasVehicleData: LiveData<String>
        get() = _hasVehicleData


    fun insertBusLocation(busLocationModel: BusLocationModel) = viewModelScope.launch {
        insertBusLocationUseCase(busLocationModel)
    }

    fun getRemoteBusLocationData(cookie: String, lineCode: Int) = viewModelScope.launch {
        getRemoteBusLocationDataUseCase(cookie, lineCode).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _busDtoLocationDataModel.value = result.data
                    updateHasVehicleData("Success")
                }
                is Resource.Error -> {
                    _busDtoLocationDataModel.value = result.data
                    updateHasVehicleData("Error")
                }
                is Resource.Loading -> {
                    _busDtoLocationDataModel.value = result.data
                }
            }
        }.launchIn(this)
    }

    private fun updateHasVehicleData(status: String) {
        _hasVehicleData.value = status
    }
}