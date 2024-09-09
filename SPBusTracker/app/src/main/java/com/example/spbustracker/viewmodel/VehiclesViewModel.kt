package com.example.spbustracker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spbustracker.model.Vehicle
import com.example.spbustracker.repository.VehicleRepository
import kotlinx.coroutines.launch

class VehiclesViewModel(private val repository: VehicleRepository) : ViewModel() {

    private val _vehicles = MutableLiveData<List<Vehicle>?>()
    val vehicles: MutableLiveData<List<Vehicle>?> = _vehicles

    fun loadVehicles() {
        viewModelScope.launch {
            try {
                val vehicleList = repository.getVehiclePositions()
                _vehicles.postValue(vehicleList)
            } catch (ex: Exception) {
                throw Exception("Erro ao buscar posições: ${ex.message}")
            }
        }
    }

}


