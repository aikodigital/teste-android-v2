package br.com.aikosptrans.presentation.buslinedetail.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.aikosptrans.domain.usecase.GetArriveForecastTimeUseCase
import br.com.aikosptrans.domain.usecase.GetBusStopByLineUseCase
import br.com.aikosptrans.util.BaseFlowViewModel
import br.com.aikosptrans.util.launchSuspend
import kotlinx.coroutines.flow.update

class BusLineDetailViewModel(
    private val getBusStopByLineUseCase: GetBusStopByLineUseCase,
    private val getArriveForecastTimeUseCase: GetArriveForecastTimeUseCase
) : BaseFlowViewModel<BusLineDetailUiState, Any>(
    BusLineDetailUiState()
){

    fun getArriveForecastTime(
        idLine: String,
        idStop: String
    ) {
        viewModelScope.launchSuspend(
            block = {
                getArriveForecastTimeUseCase(
                    idLine,
                    idStop
                )
            },
            onSuccess = {
                _uiState.update { state ->
                    state.copy(
                        shouldShowTime = true,
                        arriveForecastTime = it
                    )
                }
            }
        )
    }

    fun getBusStopByLine(idLine: String) {
        viewModelScope.launchSuspend(
            block = {
                getBusStopByLineUseCase(idLine)
            },
            onSuccess = {
                _uiState.update { state ->
                    state.copy(
                        busStops = it
                    )
                }
            },
            onError = {
                _uiState.update { state ->
                    state.copy(
                        hasError = true
                    )
                }
            }
        )
    }

    fun clearError() {
        _uiState.update { state ->
            state.copy(
                hasError = false
            )
        }
    }

    fun clearBottomSheetVisibility() {
        _uiState.update { state ->
            state.copy(
                shouldShowTime = false
            )
        }
    }
}