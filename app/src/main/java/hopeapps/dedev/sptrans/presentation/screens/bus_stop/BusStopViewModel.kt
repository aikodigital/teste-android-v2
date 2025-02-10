package hopeapps.dedev.sptrans.presentation.screens.bus_stop

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hopeapps.dedev.sptrans.domain.models.BusStop
import hopeapps.dedev.sptrans.domain.models.StaticPoint
import hopeapps.dedev.sptrans.domain.usecase.BusStopPredictionUseCase
import kotlinx.coroutines.launch

class BusStopViewModel(
    private val busStopPredictionUseCase: BusStopPredictionUseCase
) : ViewModel() {

    var state by mutableStateOf(BusStopState())
        private set

    fun onAction(action: BusStopAction) {
        when (action) {
            is BusStopAction.ViewInMapClick -> {}
            else -> {}
        }
    }

    private fun loadLineBusItems(busLineId: Int) {
        viewModelScope.launch {
            state = state.copy(isLoading = true, errorMessage = null)
            val result = busStopPredictionUseCase(busLineId)
            result.fold(
                onSuccess = { state = state.copy(busStopPrediction = it, isLoading = false) },
                onFailure = { state = state.copy(errorMessage = it.message, isLoading = false) }
            )
        }
    }


    fun load(busStop: BusStop) {
        state = state.copy(
            busStop = busStop,
            busStopPoint = StaticPoint(busStop.latitude, busStop.longitude, busStop.name)
        )
        loadLineBusItems(busLineId = busStop.idCodeBusStop)
    }
}