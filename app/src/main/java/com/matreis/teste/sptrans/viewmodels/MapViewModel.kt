package com.matreis.teste.sptrans.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matreis.teste.sptrans.R
import com.matreis.teste.sptrans.domain.model.BusStop
import com.matreis.teste.sptrans.domain.model.TimeWithVehicle
import com.matreis.teste.sptrans.domain.model.Vehicle
import com.matreis.teste.sptrans.domain.model.VehiclePosition
import com.matreis.teste.sptrans.domain.usecase.GetBusStopUseCase
import com.matreis.teste.sptrans.domain.usecase.GetVehiclePositionUseCase
import com.matreis.teste.sptrans.helper.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getBusStopUseCase: GetBusStopUseCase,
    private val getVehiclePositionUseCase: GetVehiclePositionUseCase
) : ViewModel() {

    private val _vehiclePositions = MutableLiveData<List<Vehicle>>()
    val vehiclePositions: LiveData<List<Vehicle>> get() = _vehiclePositions

    private val _busStops = MutableLiveData<List<BusStop>>()
    val busStops: LiveData<List<BusStop>> get() = _busStops

    private val _error = MutableLiveData<Event<Int>>()
    val error: LiveData<Event<Int>> get() = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val loadComplete = MutableLiveData<Boolean>()
    val loadCompleteEvent: LiveData<Boolean> get() = loadComplete

    fun getLinesInformation(lineCode: Long) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                getBusStopByLine(lineCode)
                getVehiclePositionByLine(lineCode)
                //loadComplete.postValue(true)
            }catch (e: Exception){
                e.printStackTrace()
                _error.value = Event(R.string.error_get_lines_informations)
            }
            _isLoading.value = false
        }
    }

    private suspend fun getBusStopByLine(lineCode: Long) {
        _isLoading.value = true
        coroutineScope {
            val response = getBusStopUseCase(lineCode)
            if (response.isSuccessful) {
                _busStops.value = response.body()
                //_isLoading.value = false
            } /*else {
                _error.value = Event(R.string.error_get_bus_stops)
            }*/
        }
    }

    private suspend fun getVehiclePositionByLine(lineCode: Long) {
        coroutineScope {
            val response = getVehiclePositionUseCase(lineCode)
            if (response.isSuccessful) {
                _vehiclePositions.value = response.body()?.vehicles
                //_isLoading.value = false
            } else {
                _error.value = Event(R.string.error_get_vehicle_positions)
                //_isLoading.value = false
            }
        }
    }

}

