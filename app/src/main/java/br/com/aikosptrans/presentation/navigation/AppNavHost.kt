package br.com.aikosptrans.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.aikosptrans.presentation.splash.SplashPage

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: AppDestination = AppDestination.Splash
) {

    NavHost(
        navController = navController,
        startDestination = startDestination.createRoute()
    ) {

        composable(
            route = AppDestination.Splash.createRoute()
        ) {
            SplashPage()
        }
    }
}