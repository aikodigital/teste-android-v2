package br.com.aikosptrans.presentation.busline.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.aikosptrans.domain.usecase.GetBusLineUseCase
import br.com.aikosptrans.util.BaseFlowViewModel
import br.com.aikosptrans.util.launchSuspend
import kotlinx.coroutines.flow.update

class BusLineViewModel(
    private val getBusLineUseCase: GetBusLineUseCase
) : BaseFlowViewModel<BusLineUiState, Any> (
    BusLineUiState()
){

    fun getBusLine(query: String) {
        viewModelScope.launchSuspend(
            block = {
                getBusLineUseCase(query)
            },
            onSuccess = {
                _uiState.update { state ->
                    state.copy(
                        lines = it
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

    fun onQueryChanged(query: String) {
        _uiState.update { state ->
            state.copy(
                query = query
            )
        }
        getBusLine(query)
    }

    fun clearError() {
        _uiState.update { state ->
            state.copy(
                hasError = false
            )
        }
    }
}