package hopeapps.dedev.sptrans.presentation.line_bus

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hopeapps.dedev.sptrans.data.model.BusLine
import hopeapps.dedev.sptrans.domain.usecase.BusStopByIdLineUseCase
import kotlinx.coroutines.launch

class LineBusViewModel (
    private val lineBusUseCase: BusStopByIdLineUseCase
) : ViewModel() {

    var state by mutableStateOf(LineBusState())
        private set

    fun onAction(action: LineBusAction) {
        when (action) {
            is LineBusAction.ViewInMapClick -> { }
            else -> {}
        }
    }

    private fun loadBusStopItems(busLineId: Int) {
        viewModelScope.launch {
            state = state.copy(isLoading = true, errorMessage = null)
            val result = lineBusUseCase(busLineId)
            result.fold(
                onSuccess = { state = state.copy(busStopItems = it, isLoading = false) },
                onFailure = { state = state.copy(errorMessage = it.message, isLoading = false) }
            )
        }
    }


    fun load(busLine: BusLine) {
        state = state.copy(
            busLine = busLine
        )
        loadBusStopItems(busLineId = busLine.lineId ?: 0)
    }
}