package br.com.aikosptrans.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.aikosptrans.presentation.busline.BusLinePage
import br.com.aikosptrans.presentation.busmap.BusMapPage
import br.com.aikosptrans.presentation.busstopmap.BusStopMapPage
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
            SplashPage(
                navController = navController
            )
        }

        composable(
            route = AppDestination.BusMap.createRoute()
        ) {
            BusMapPage(
                navController = navController
            )
        }

        composable(
            route = AppDestination.StopBusMap.createRoute()
        ) {
            BusStopMapPage(
                navController = navController
            )
        }

        composable(
            route = AppDestination.BusLine.createRoute()
        ) {
            BusLinePage(
                navController = navController
            )
        }
    }
}