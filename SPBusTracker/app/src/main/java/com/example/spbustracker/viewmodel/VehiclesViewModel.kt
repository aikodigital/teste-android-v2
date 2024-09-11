
package com.example.spbustracker.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spbustracker.model.Line
import com.example.spbustracker.model.Vehicle
import com.example.spbustracker.repository.VehicleRepository
import kotlinx.coroutines.launch

class VehiclesViewModel(private val repository: VehicleRepository) : ViewModel() {

    private val _vehicles = MutableLiveData<List<Pair<Line, Vehicle>>?>()
    val vehicles: MutableLiveData<List<Pair<Line, Vehicle>>?> get() = _vehicles

    private val _filteredVehicles = MutableLiveData<List<Pair<Line, Vehicle>>?>()
    val filteredVehicles: MutableLiveData<List<Pair<Line, Vehicle>>?> get() = _filteredVehicles

    fun loadVehicles() {
        viewModelScope.launch {
            try {
                val vehicleData = repository.getVehiclePositions()
                if (!vehicleData.isNullOrEmpty()) {
                    _vehicles.postValue(vehicleData)
                    _filteredVehicles.postValue(vehicleData)
                }
            } catch (e: Exception) {
                Log.e("VehiclesViewModel", "Erro ao carregar veículos: ${e.message}")
            }
        }
    }

    fun searchVehicles(lineNumber: String) {
        val vehiclesList = _vehicles.value
        if (!vehiclesList.isNullOrEmpty()) {
            val filtered = vehiclesList.filter { (line, _) ->
                line.c.contains(lineNumber, ignoreCase = true)
            }
            _filteredVehicles.postValue(filtered)
        }
    }
}
