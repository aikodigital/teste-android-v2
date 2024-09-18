package br.com.aikosptrans.presentation.busmap.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.aikosptrans.domain.entity.ClusterData
import br.com.aikosptrans.domain.usecase.GetBusLocationUseCase
import br.com.aikosptrans.util.BaseFlowViewModel
import br.com.aikosptrans.util.launchSuspend
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class BusMapViewModel(
    private val getBusLocationUseCase: GetBusLocationUseCase,
) : BaseFlowViewModel<BusMapUiState, Any> (
    BusMapUiState()
){

    fun getBusLocation() {
        viewModelScope.launch {
            while(isActive) {
                getBusLocationRequest()
                delay(3.0.seconds)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    private fun getBusLocationRequest() {
        viewModelScope.launchSuspend(
            block = {
                getBusLocationUseCase()
            },
            onSuccess = {
                val clusterData = it.map { bus ->
                    ClusterData(
                        location = LatLng(
                            bus.busDetail.latitude,
                            bus.busDetail.longitude
                        ),
                        name = "Letreiro Completo: ${bus.busLine.fullNumber}",
                        description = "Destino: ${bus.busLine.destination}"
                    )
                }
                _uiState.update { state ->
                    state.copy(
                        buses = clusterData
                    )
                }
            }
        )
    }
}