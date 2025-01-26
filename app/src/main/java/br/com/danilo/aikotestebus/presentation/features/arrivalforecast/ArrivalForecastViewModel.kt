package br.com.danilo.aikotestebus.presentation.features.arrivalforecast

import androidx.lifecycle.viewModelScope
import br.com.danilo.aikotestebus.domain.usecase.ArrivalForecastUseCase
import br.com.danilo.aikotestebus.presentation.util.BaseViewModel
import br.com.danilo.aikotestebus.presentation.util.state.ArrivalForecastState
import br.com.danilo.aikotestebus.presentation.util.state.MapLocationBusState
import kotlinx.coroutines.Job
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class ArrivalForecastViewModel(
    private val useCase: ArrivalForecastUseCase
) : BaseViewModel<ArrivalForecastState>(ArrivalForecastState.Loading) {

    private var periodicTaskJob: Job? = null

    fun startPeriodicTask(idStop: Int, idLine: Int) {
        if (periodicTaskJob?.isActive != true) {
            periodicTaskJob = viewModelScope.launch {
                var retryDelay = 6000L
                while (isActive) {
                    try {
                        withTimeout(5000) {
                            fetchArrivalForecast(idStop, idLine)
                        }

                        retryDelay = 6000L
                    } catch (e: TimeoutCancellationException) {
                        retryDelay *= 2
                        uiStateAccess.value = ArrivalForecastState.Error
                    }

                    delay(retryDelay)
                }
            }
        }
    }

    fun stopPeriodicTask() {
        periodicTaskJob?.cancel()
    }

    fun fetchArrivalForecast(idStop: Int, idLine: Int) {
        viewModelScope.launch {
            uiStateAccess.value = ArrivalForecastState.Loading
            useCase.getArrivalForecastTime(idStop, idLine).collect { result ->
                result.fold(
                    onSuccess = { data ->
                        uiStateAccess.value = ArrivalForecastState.Success(data)
                    },
                    onFailure = {
                        uiStateAccess.value = ArrivalForecastState.Error
                    }
                )
            }
        }
    }
}