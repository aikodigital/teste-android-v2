package br.com.danilo.aikotestebus.presentation.features.maplocation

import androidx.lifecycle.viewModelScope
import br.com.danilo.aikotestebus.domain.usecase.MapLocationBusUseCase
import br.com.danilo.aikotestebus.presentation.util.BaseViewModel
import br.com.danilo.aikotestebus.presentation.util.MAP_RETRY_DELAY
import br.com.danilo.aikotestebus.presentation.util.MAP_TIMEOUT_DELAY
import br.com.danilo.aikotestebus.presentation.util.TWO
import br.com.danilo.aikotestebus.presentation.util.state.MapLocationBusState
import kotlinx.coroutines.Job
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class MapLocationBusViewModel(
    private val useCase: MapLocationBusUseCase
) : BaseViewModel<MapLocationBusState>(MapLocationBusState.InitialState) {

    private var periodicTaskJob: Job? = null

    fun startPeriodicLocationTask() {
        if (periodicTaskJob?.isActive != true) {
            periodicTaskJob = viewModelScope.launch {
                var retryDelay = MAP_RETRY_DELAY
                while (isActive) {
                    try {
                        withTimeout(MAP_TIMEOUT_DELAY) {
                            fetchLocation()
                        }

                        retryDelay = MAP_RETRY_DELAY
                    } catch (e: TimeoutCancellationException) {
                        retryDelay *= TWO
                        uiStateAccess.value = MapLocationBusState.Error
                    }

                    delay(retryDelay)
                }
            }
        }
    }

    fun stopPeriodicLocationTask() {
        periodicTaskJob?.cancel()
    }

    private suspend fun fetchLocation() {
        val result = useCase.getBusesPosition()
        result.collect { collect ->
            collect.fold(
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