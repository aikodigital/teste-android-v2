package com.example.spbustracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spbustracker.repository.VehicleRepository

class VehiclesViewModelFactory(
    private val repository: VehicleRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VehiclesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VehiclesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

