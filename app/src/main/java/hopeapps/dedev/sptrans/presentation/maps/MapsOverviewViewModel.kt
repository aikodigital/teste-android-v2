package hopeapps.dedev.sptrans.presentation.maps

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hopeapps.dedev.sptrans.domain.models.ActionPoint
import hopeapps.dedev.sptrans.domain.models.DynamicPoint
import hopeapps.dedev.sptrans.domain.models.MapPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OverviewMapsViewModel(
) : ViewModel() {


    var state by mutableStateOf(OverviewMapsState())
        private set


    fun load(mapPoints: List<MapPoint>) {

        val hasActionPoint = mapPoints.filterIsInstance<ActionPoint>().isNotEmpty()
        val dynamicPoints = mapPoints.filterIsInstance<DynamicPoint>().isNotEmpty()

        if (hasActionPoint) {
            loadAllPoints()
        } else {
            loadStaticOrDynamicsPoints(mapPoints)
        }
        state = state.copy(
//            locations = markerList
        )
    }

    private fun loadStaticOrDynamicsPoints(mapPoints: List<MapPoint>) {
        val isDynamicPoints = mapPoints.filterIsInstance<DynamicPoint>().isNotEmpty()
        if (isDynamicPoints) {
            while (true) {
                viewModelScope.launch {
                    delay(5000)
                    // Puxa dados de das posições dos veiculos e coloca no state.
                }


            }
        } else {
//            state.locations
        }

    }


    private fun loadAllPoints() {
        //Deixar carregar todos os pontos vindos da API
    }


    init {
        viewModelScope.launch {
//            val result = overviewMapsRepository.getBusLocations()
//            state = state.copy(busLocations = result.toClusterBusLines())
        }
    }
}