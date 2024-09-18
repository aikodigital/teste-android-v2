package br.com.aikosptrans.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.aikosptrans.domain.entity.BusLine
import br.com.aikosptrans.presentation.busline.BusLinePage
import br.com.aikosptrans.presentation.buslinedetail.BusLineDetailPage
import br.com.aikosptrans.presentation.busmap.BusMapPage
import br.com.aikosptrans.presentation.busstopmap.BusStopMapPage
import br.com.aikosptrans.presentation.splash.SplashPage
import br.com.aikosptrans.util.decodeStringInKObject

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

        val BUS_LINE_ARGS = "busLine"
        composable(
            route = "${AppDestination.BusLineDetail().route}/{$BUS_LINE_ARGS}",
            arguments = listOf(
                navArgument(BUS_LINE_ARGS) {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) {
            val busLine = it.arguments
                ?.getString(BUS_LINE_ARGS)
                ?.decodeStringInKObject<BusLine>()

            BusLineDetailPage(
                navController = navController,
                busLine = busLine
            )
        }
    }
}