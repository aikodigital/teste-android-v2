package hopeapps.dedev.sptrans.presentation.maps

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hopeapps.dedev.sptrans.domain.models.ActionPoint
import hopeapps.dedev.sptrans.domain.models.DynamicPoint
import hopeapps.dedev.sptrans.domain.models.Location
import hopeapps.dedev.sptrans.domain.models.MapPoint
import hopeapps.dedev.sptrans.domain.models.StaticPoint
import hopeapps.dedev.sptrans.domain.usecase.AllVehiclesPositionUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class OverviewMapsViewModel(
    private val allVehiclesPositionUseCase: AllVehiclesPositionUseCase
) : ViewModel() {

    var state by mutableStateOf(OverviewMapsState())
        private set

    fun load(mapPoint: MapPoint) {
        state = when (mapPoint) {
            is DynamicPoint -> state.copy(
                focusLocation = Location(mapPoint.latitude, mapPoint.longitude)
            ).also { startDynamicPointsUpdate(mapPoint.id) }

            is StaticPoint -> state.copy(
                busStopLocation = mapPoint,
                focusLocation = Location(mapPoint.latitude, mapPoint.longitude)
            ).also { startAllPointsUpdate() }

            ActionPoint -> {
                state = state.copy(
                    focusLocation = Location(
                        lat = -23.550520,
                        long = -46.633309
                    )
                )
                state.also {
                    startAllPointsUpdate()
                }
            }
        }
    }


    private fun startAllPointsUpdate() {
        viewModelScope.launch {
            while (isActive) {
                updateDynamicPoints()
                delay(6000)
            }
        }
    }

    private fun startDynamicPointsUpdate(dynamicPointId: Int) {
        viewModelScope.launch {
            while (isActive) {
                updateDynamicPoints(dynamicPointId)
                delay(6000)
            }
        }
    }

    private suspend fun updateDynamicPoints(filterId: Int? = null) {
        val result = allVehiclesPositionUseCase()
        result.fold(
            onSuccess = { response ->
                val newFocus = filterId?.let { id -> response.firstOrNull { it.id == id } }
                state = state.copy(
                    dynamicPoints = response,
                    focusLocation = newFocus?.let { Location(it.latitude, it.longitude) }
                        ?: state.focusLocation
                )
            },
            onFailure = { state = state.copy(errorMessage = it.message ?: "") }
        )
    }
}