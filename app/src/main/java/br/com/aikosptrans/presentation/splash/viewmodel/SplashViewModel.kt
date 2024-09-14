package br.com.aikosptrans.presentation.splash.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.aikosptrans.domain.usecase.AuthenticateUserUseCase
import br.com.aikosptrans.util.BaseFlowViewModel
import br.com.aikosptrans.util.launchSuspend
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel(
    private val authenticateUserUseCase: AuthenticateUserUseCase
) : BaseFlowViewModel<SplashUiState, SplashState> (
    SplashUiState()
){
    fun authenticateUser() {
        viewModelScope.launchSuspend(
            block = {
                authenticateUserUseCase()
            },
            onError = {
                _uiState.update { state ->
                    state.copy(
                        hasError = true
                    )
                }
            },
            onSuccess = {
                viewModelScope.launch {
                    _state.emit(
                        SplashState.GoToHome
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
}