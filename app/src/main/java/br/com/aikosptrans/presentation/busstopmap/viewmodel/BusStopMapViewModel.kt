package br.com.aikosptrans.presentation.busstopmap.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.aikosptrans.domain.entity.ClusterData
import br.com.aikosptrans.domain.usecase.GetBusStopUseCase
import br.com.aikosptrans.util.BaseFlowViewModel
import br.com.aikosptrans.util.launchSuspend
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.update

class BusStopMapViewModel(
    private val getBusStopUseCase: GetBusStopUseCase
) : BaseFlowViewModel<BusStopMapUiState, Any> (
    BusStopMapUiState()
){

    fun getBusStop(query: String = "") {
        viewModelScope.launchSuspend(
            block = {
                getBusStopUseCase(query)
            },
            onSuccess = {
                val clusterData = it.map { busStop ->
                    ClusterData(
                        location = LatLng(
                            busStop.latitude,
                            busStop.longitude
                        ),
                        name = "Nome: ${busStop.name}",
                        description = "Endereço: ${busStop.address}"
                    )
                }

                _uiState.update { state ->
                    state.copy(
                        busStops = clusterData
                    )
                }
            }
        )
    }

    fun onQueryChanged(query: String) {
        _uiState.update { state ->
            state.copy(
                query = query
            )
        }
        getBusStop(query)
    }
}