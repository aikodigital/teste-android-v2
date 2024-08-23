package com.matreis.teste.sptrans.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matreis.teste.sptrans.R
import com.matreis.teste.sptrans.domain.model.BusStop
import com.matreis.teste.sptrans.domain.model.Line
import com.matreis.teste.sptrans.domain.model.TimeWithVehicle
import com.matreis.teste.sptrans.domain.model.Vehicle
import com.matreis.teste.sptrans.domain.model.VehiclePosition
import com.matreis.teste.sptrans.domain.usecase.GetBusStopUseCase
import com.matreis.teste.sptrans.domain.usecase.GetVehiclePositionUseCase
import com.matreis.teste.sptrans.helper.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getBusStopUseCase: GetBusStopUseCase,
    private val getVehiclePositionUseCase: GetVehiclePositionUseCase
) : ViewModel() {

    private var keepUpdating = true

    private val _selectedLine = MutableLiveData<Line>()
    val selectedLine: LiveData<Line> get() = _selectedLine

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

    private val _markers = MutableLiveData<Pair<List<BusStop>,List<Vehicle>>>()
    val markers: LiveData<Pair<List<BusStop>,List<Vehicle>>> get() = _markers

    private var busStopList = listOf<BusStop>()

    fun getLinesInformation(lineCode: Long) {
        viewModelScope.launch {
            keepUpdating = true
            _isLoading.value = true
            try {
                val busStops = getBusStopByLine(lineCode)
                busStopList = busStops
                do {
                    Log.i("MapViewModel", "Getting vehicle positions for line: $lineCode")
                    val vehiclePositions = getVehiclePositionByLine(lineCode)
                    _markers.postValue(Pair(busStopList, vehiclePositions))
                    delay(5000)
                }while (keepUpdating && isActive)
                //loadComplete.postValue(true)
            }catch (e: Exception){
                e.printStackTrace()
                _error.value = Event(R.string.error_get_lines_informations)
            }
            _isLoading.value = false
        }
    }

    private suspend fun getBusStopByLine(lineCode: Long): List<BusStop> {
        //_isLoading.value = true
        /*coroutineScope {

        }*/
        val response = getBusStopUseCase(lineCode)
        /*if (response.isSuccessful) {
            _busStops.value = response.body()
            //_isLoading.value = false
        }*/ /*else {
                _error.value = Event(R.string.error_get_bus_stops)
            }*/
        return response.body() ?: emptyList()
    }

    private suspend fun getVehiclePositionByLine(lineCode: Long): List<Vehicle> {
        /*coroutineScope {
            val response = getVehiclePositionUseCase(lineCode)
            if (response.isSuccessful) {
                _vehiclePositions.value = response.body()?.vehicles
                //_isLoading.value = false
            } else {
                _error.value = Event(R.string.error_get_vehicle_positions)
                //_isLoading.value = false
            }
        }*/
        return getVehiclePositionUseCase(lineCode).body()?.vehicles ?: emptyList()
    }

    fun setSelectedLine(it: Line) {
        _selectedLine.value = it
    }

    fun clearData() {
        keepUpdating = false
        _busStops.value = emptyList()
        _vehiclePositions.value = emptyList()
    }

    fun getBusStopList(): List<BusStop> = busStopList

}

