package br.com.danilo.aikotestebus.presentation.util.state
sealed class SplashState {
    data object LoadingSplash : SplashState()
    data class Error(val throwable: Throwable?) : SplashState()
    data object Success : SplashState()
}