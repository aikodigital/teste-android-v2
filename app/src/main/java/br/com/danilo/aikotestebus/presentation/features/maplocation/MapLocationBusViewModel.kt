package br.com.danilo.aikotestebus.presentation.features.maplocation

import androidx.lifecycle.viewModelScope
import br.com.danilo.aikotestebus.domain.usecase.MapLocationBusUseCase
import br.com.danilo.aikotestebus.presentation.util.BaseViewModel
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

    fun startPeriodicTask() {
        if (periodicTaskJob?.isActive != true) {
            periodicTaskJob = viewModelScope.launch {
                var retryDelay = 2500L
                while (isActive) {
                    try {
                        withTimeout(5000) {
                            fetchLocation()
                        }

                        retryDelay = 2500L
                    } catch (e: TimeoutCancellationException) {
                        retryDelay = minOf(retryDelay * 2, 30000L)
                        uiStateAccess.value = MapLocationBusState.Error
                    }

                    delay(retryDelay)
                }
            }
        }
    }

    fun stopPeriodicTask() {
        periodicTaskJob?.cancel()
    }

    private suspend fun fetchLocation() {
        val result = useCase.getBusesPosition()
        result.collect { result ->
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