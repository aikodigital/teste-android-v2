package com.example.spbustracker.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spbustracker.model.Line
import com.example.spbustracker.model.Vehicle
import com.example.spbustracker.repository.VehicleRepository
import kotlinx.coroutines.launch

class VehiclesViewModel(private val repository: VehicleRepository) : ViewModel() {

    private val _vehicles = MutableLiveData<List<Pair<Line, Vehicle>>>()
    val vehicles: LiveData<List<Pair<Line, Vehicle>>> get() = _vehicles

    fun loadVehicles() {
        viewModelScope.launch {
            try {
                val vehicleData = repository.getVehiclePositions()
                _vehicles.postValue(vehicleData!!)
            } catch (e: Exception) {
                Log.e("VehiclesViewModel", "Erro ao carregar veículos: ${e.message}")
            }
        }
    }

}



