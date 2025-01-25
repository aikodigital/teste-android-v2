package br.com.danilo.aikotestebus.presentation.features.maplocation

import androidx.lifecycle.viewModelScope
import br.com.danilo.aikotestebus.domain.usecase.MapLocationBusUseCase
import br.com.danilo.aikotestebus.presentation.util.BaseViewModel
import br.com.danilo.aikotestebus.presentation.util.state.MapLocationBusState
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MapLocationBusViewModel(
    private val useCase: MapLocationBusUseCase
) : BaseViewModel<MapLocationBusState>(MapLocationBusState.InitialState) {

    init {
        startPeriodicTask()
    }

    fun startPeriodicTask() {
        viewModelScope.launch {
            while (isActive) {
                delay(20000)
                fetchLocation()
            }
        }
    }

    private fun fetchLocation() {
        viewModelScope.launch {
            useCase.getBusesPosition().collect { result ->
                result.fold(
                    onSuccess = { data ->
                        uiStateAccess.value = MapLocationBusState.Success(data)
                    },
                    onFailure = {
                        uiStateAccess.value = MapLocationBusState.Error
                    }
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}