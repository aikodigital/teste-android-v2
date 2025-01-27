package br.com.danilo.aikotestebus.presentation.features.authenticator

import androidx.lifecycle.viewModelScope
import br.com.danilo.aikotestebus.domain.usecase.AuthenticatorUseCase
import br.com.danilo.aikotestebus.presentation.util.BaseViewModel
import br.com.danilo.aikotestebus.presentation.util.state.AuthenticatorState
import kotlinx.coroutines.launch

class AuthenticatorViewModel(
    private val useCase: AuthenticatorUseCase
) : BaseViewModel<AuthenticatorState>(AuthenticatorState.InitialState) {

    fun fetchAuthenticator() {
        viewModelScope.launch {
            uiStateAccess.value = AuthenticatorState.Loading
            useCase.authenticate().collect { result ->
                result.fold(
                    onSuccess = { data ->
                        if (data) {
                            uiStateAccess.value = AuthenticatorState.Success
                        } else {
                            uiStateAccess.value = AuthenticatorState.Success
                        }
                    },
                    onFailure = { error ->
                        uiStateAccess.value = AuthenticatorState.Error(error)
                    }
                )
            }
        }
    }
}