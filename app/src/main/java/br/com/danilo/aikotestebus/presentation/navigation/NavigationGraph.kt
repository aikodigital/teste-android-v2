package br.com.danilo.aikotestebus.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.danilo.aikotestebus.domain.mapper.handleOptional
import br.com.danilo.aikotestebus.presentation.features.TabScreen
import br.com.danilo.aikotestebus.presentation.features.arrivalforecast.ArrivalForecastScreen
import br.com.danilo.aikotestebus.presentation.features.authenticator.AuthenticatorScreen
import br.com.danilo.aikotestebus.presentation.features.stopbyline.BusStopByLineScreen
import br.com.danilo.aikotestebus.presentation.util.decodeLineDetailItem
import br.com.danilo.aikotestebus.presentation.util.decodeStopDetailItem
import com.google.android.gms.maps.model.LatLng

private const val LATITUTE_SP = "-23.5489"
private const val LONGITUDE_SP = "-46.6388"

@Composable
fun NavigationGraph(
    initialRoute: String,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = initialRoute
    ) {
        composable(route = BusRoute.BusAuthenticator.route) {
            AuthenticatorScreen(navController)
        }

        composable(
            route = BusRoute.BusTabContainer.route,
            arguments = listOf(
                navArgument("latitude") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = LATITUTE_SP
                },
                navArgument("longitude") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = LONGITUDE_SP
                }
            )
        ) { entry ->
            val latitude = entry.arguments?.getString("latitude")?.toDoubleOrNull()
            val longitude = entry.arguments?.getString("longitude")?.toDoubleOrNull()
            val coord = LatLng(
                latitude ?: LATITUTE_SP.toDouble(),
                longitude ?: LONGITUDE_SP.toDouble()
            )

            TabScreen(coord, 0, navController)
        }

        composable(
            route = BusRoute.BusStopByLine.route,
            arguments = listOf(
                navArgument("item") { type = NavType.StringType }
            )
        ) { entry ->
            val item = entry.arguments?.getString("item")
            if (!item.isNullOrEmpty()) {
                BusStopByLineScreen(
                    decodeLineDetailItem(item),
                    navController
                )
            }
        }

        composable(
            route = BusRoute.BusArrivalForecastTime.route,
            arguments = listOf(
                navArgument("idStop") { type = NavType.StringType },
                navArgument("idLine") { type = NavType.StringType },
                navArgument("item") { type = NavType.StringType }
            )
        ) { entry ->
            val idStop = entry.arguments?.getString("idStop")
            val idLine = entry.arguments?.getString("idLine")
            val stopDetail = entry.arguments?.getString("item")
            if (!idStop.isNullOrEmpty() && !idLine.isNullOrEmpty() && !stopDetail.isNullOrEmpty()) {
                ArrivalForecastScreen(
                    idStop.toIntOrNull().handleOptional(),
                    idLine.toIntOrNull().handleOptional(),
                    decodeStopDetailItem(stopDetail),
                    navController
                )
            }
        }
    }
}