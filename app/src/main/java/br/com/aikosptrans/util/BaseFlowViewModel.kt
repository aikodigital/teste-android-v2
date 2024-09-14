package br.com.aikosptrans.util

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseFlowViewModel<UIState, State>(
    initialUiState: UIState
) : ViewModel() {

    protected val _uiState = MutableStateFlow(initialUiState)
    val uiState: StateFlow<UIState> = _uiState.asStateFlow()

    protected val _state = MutableSharedFlow<State>()
    val state: SharedFlow<State> = _state.asSharedFlow()
}