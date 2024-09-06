package com.example.spbustracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spbustracker.repository.OlhoVivoApi
import com.example.spbustracker.model.Vehicle

class VehiclesViewModel : ViewModel() {

    private val _vehicles = MutableLiveData<List<Vehicle>>()
    val vehicles: LiveData<List<Vehicle>> = _vehicles

    fun searchLine(lineNumber: String) {
        OlhoVivoApi.getVehiclesByLine(lineNumber) { vehicleList ->
            _vehicles.postValue(vehicleList)
        }
    }
}
