package br.com.danilo.aikotestebus.presentation.features.splash

import androidx.lifecycle.viewModelScope
import br.com.danilo.aikotestebus.domain.usecase.SplashUseCase
import br.com.danilo.aikotestebus.presentation.util.BaseViewModel
import br.com.danilo.aikotestebus.presentation.util.state.SplashState
import kotlinx.coroutines.launch

class SplashViewModel(
    private val useCase: SplashUseCase
) : BaseViewModel<SplashState>(SplashState.LoadingSplash) {

    fun fetchAuthenticator() {
        viewModelScope.launch {
            uiStateAccess.value = SplashState.LoadingSplash
            useCase.authenticatorSplash().collect { result ->
                result.fold(
                    onSuccess = { data ->
                        if (data) {
                            uiStateAccess.value = SplashState.Success
                        } else {
                            uiStateAccess.value = SplashState.Error(null)
                        }

                    },
                    onFailure = { error ->
                        uiStateAccess.value = SplashState.Error(error)
                    }
                )
            }
        }
    }
}