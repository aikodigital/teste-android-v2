package br.com.danilo.aikotestebus.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.danilo.aikotestebus.presentation.features.TabScreen
import br.com.danilo.aikotestebus.presentation.features.authenticator.AuthenticatorScreen

@Composable
fun NavigationGraph(
    initialRoute: String,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = initialRoute
    ) {
        composable(route = BusRoute.BusSplash.route) {
            AuthenticatorScreen(navController)
        }



        composable(route = BusRoute.BusTabContainer.route) {
            TabScreen()
        }
    }
}