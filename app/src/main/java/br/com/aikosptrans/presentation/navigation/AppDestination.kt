package br.com.aikosptrans.presentation.navigation

sealed class AppDestination(val route: String) {
    abstract fun createRoute(): String

    data object Splash : AppDestination("SPLASH_SCREEN") {
        override fun createRoute(): String = route
    }
}