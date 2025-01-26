package br.com.danilo.aikotestebus.presentation.features.maplocation

import androidx.lifecycle.viewModelScope
import br.com.danilo.aikotestebus.domain.usecase.MapLocationBusUseCase
import br.com.danilo.aikotestebus.presentation.util.BaseViewModel
import br.com.danilo.aikotestebus.presentation.util.state.MapLocationBusState
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MapLocationBusViewModel(
    private val useCase: MapLocationBusUseCase
) : BaseViewModel<MapLocationBusState>(MapLocationBusState.InitialState) {

    private var periodicTaskJob: Job? = null

    fun startPeriodicTask() {
        if (periodicTaskJob?.isActive != true) {
            periodicTaskJob = viewModelScope.launch {
                while (isActive) {
                    delay(2500)
                    fetchLocation()
                }
            }
        }
    }

    fun stopPeriodicTask() {
        periodicTaskJob?.cancel()
    }

    private fun fetchLocation() {
        viewModelScope.launch {
            useCase.getBusesPosition().collect { result ->
                result.fold(
                    onSuccess = { data ->
                        if (data.busList.isEmpty()) {
                            uiStateAccess.value = MapLocationBusState.Error
                        } else {
                            uiStateAccess.value = MapLocationBusState.Success(data)
                        }
                    },
                    onFailure = {
                        uiStateAccess.value = MapLocationBusState.Error
                    }
                )
            }
        }
    }
}