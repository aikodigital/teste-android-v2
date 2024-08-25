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

    private val _busDtoCorridorDataModel = MutableLiveData<BusCorridorModel?>()

    val busDtoCorridorDataModel: MutableLiveData<BusCorridorModel?>
        get() = _busDtoCorridorDataModel

    fun getRemoteBusCorridorData(cookie: String) = viewModelScope.launch {
        getRemoteBusCorridorUseCase(cookie).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _busDtoCorridorDataModel.value = result.data
                    println("RESULTADO DA API: ${result.data?.nomeCorredor}")
                    println("RESULTADO DA API: ${result.data?.codigoCorredor}")
                    println("API SUCCESS")
                }
                is Resource.Error -> {
                    _busDtoCorridorDataModel.value = result.data
                    println("API ERROR")
                }
                is Resource.Loading -> {
                    _busDtoCorridorDataModel.value = result.data
                    println("API LOADING")
                }
            }
        }.launchIn(this)
    }

}