package br.com.aikosptrans.presentation.splash.viewmodel

sealed class SplashState {
    data object GoToHome : SplashState()
}