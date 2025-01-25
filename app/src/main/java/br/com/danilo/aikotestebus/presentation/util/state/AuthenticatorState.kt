package br.com.danilo.aikotestebus.presentation.util.state
sealed class AuthenticatorState {
    data object Loading : AuthenticatorState()
    data class Error(val throwable: Throwable?) : AuthenticatorState()
    data object Success : AuthenticatorState()
    data object InitialState : AuthenticatorState()
}