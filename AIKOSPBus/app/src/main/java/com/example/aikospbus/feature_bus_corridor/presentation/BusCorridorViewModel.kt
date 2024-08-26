package com.example.aikospbus.feature_bus_corridor.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aikospbus.feature_bus_corridor.domain.model.BusCorridorModel
import com.example.aikospbus.feature_bus_corridor.domain.useCase.GetRemoteBusCorridorUseCase
import com.example.aikospbus.feature_bus_corridor.domain.useCase.InsertBusCorridorUseCase
import com.example.aikospbus.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusCorridorViewModel @Inject constructor(
    private val insertBusCorridorUseCase: InsertBusCorridorUseCase,
    private val getRemoteBusCorridorUseCase: GetRemoteBusCorridorUseCase
) : ViewModel() {

    var busCorridorLiveData: MutableLiveData<List<BusCorridorModel>?> = MutableLiveData()

    fun getRemoteBusCorridorData(cookie: String) = viewModelScope.launch {
        getRemoteBusCorridorUseCase(cookie).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    busCorridorLiveData.value = result.data
                }
                is Resource.Error -> {
                    busCorridorLiveData.value = result.data
                }
                is Resource.Loading -> {
                    busCorridorLiveData.value = result.data
                }
            }
        }.launchIn(this)
    }

}