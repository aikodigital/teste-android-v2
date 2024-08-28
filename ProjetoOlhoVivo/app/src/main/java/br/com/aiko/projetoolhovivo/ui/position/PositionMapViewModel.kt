package br.com.aiko.projetoolhovivo.ui.position

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.aiko.projetoolhovivo.data.model.position.PositionLine
import br.com.aiko.projetoolhovivo.data.model.vehicle.Vehicle
import br.com.aiko.projetoolhovivo.domain.usecase.position.PositionUseCase
import br.com.aiko.projetoolhovivo.domain.usecase.stop.StopUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class PositionMapViewModel @Inject constructor(
    private val positionUseCase: PositionUseCase,
    private val stopUseCase: StopUseCase
) : ViewModel() {
    var token: String = ""

    private var _isLoading = MutableLiveData(false)
    private val _error = MutableLiveData<String?>(null)
    private val _position_map = MutableLiveData<PositionMap?>(null)
    private val _lines = MutableLiveData<ArrayList<PositionLine>>(arrayListOf())

    val isLoading: LiveData<Boolean> get() = _isLoading
    val error: LiveData<String?> get() = _error
    val positionMap: LiveData<PositionMap?> get() = _position_map
    val lines: LiveData<ArrayList<PositionLine>> get() = _lines

    fun getPositionByListLines() = viewModelScope.launch {
        _isLoading.postValue(true)
        positionUseCase.getPositionByListLines(token).onSuccess { position ->
            val vehiclesTemp: ArrayList<Vehicle> = arrayListOf()
            val linesTemp: ArrayList<PositionLine> = arrayListOf()
            for (pl in position.lines) {
                if (linesTemp.none { lt -> lt.code == pl.code })
                    linesTemp.add(pl)
                for (vehicle in pl.vehicles) {
                    if (vehiclesTemp.none { vt -> vt.prefixVehicle == vehicle.prefixVehicle })
                        vehiclesTemp.add(vehicle)
                }
            }
            stopUseCase.getStops(token).onSuccess {stops ->
                val positionMap = PositionMap(stops, vehiclesTemp, position.lastUpdate)
                _lines.postValue(linesTemp)
                _position_map.postValue(positionMap)
                _isLoading.postValue(false)
            }.onFailure { fail ->
                run {
                    _isLoading.postValue(false)
                    _error.postValue(fail.stackTraceToString())
                }
            }
        }.onFailure { fail ->
            run {
                _isLoading.postValue(false)
                _error.postValue(fail.stackTraceToString())
            }
        }
    }

    fun getPositionByVehicles(codeLine: Int) = viewModelScope.launch {
        _isLoading.postValue(true)
        positionUseCase.getPositionByVehicles(token, codeLine).onSuccess { position ->
            val vehiclesTemp: ArrayList<Vehicle> = arrayListOf()
            for (vehicle in position.vehicles) {
                if (vehiclesTemp.none { vt -> vt.prefixVehicle == vehicle.prefixVehicle })
                    vehiclesTemp.add(vehicle)
            }

            if(_position_map.value == null) {
                val positionMap = PositionMap(arrayListOf(), vehiclesTemp, position.lastUpdate)
                _position_map.postValue(positionMap)
            } else {
                val positionMap = _position_map.value!!
                positionMap.lastUpdate = position.lastUpdate
                positionMap.vehicles = vehiclesTemp
                _position_map.postValue(positionMap)
            }
            _isLoading.postValue(false)
        }.onFailure { fail ->
            run {
                _isLoading.postValue(false)
                _error.postValue(fail.stackTraceToString())
            }
        }
    }
}