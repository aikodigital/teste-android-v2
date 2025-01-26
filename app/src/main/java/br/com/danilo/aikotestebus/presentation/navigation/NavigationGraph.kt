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
import br.com.danilo.aikotestebus.presentation.features.arrivalforecast.ArrivalMapScreen
import br.com.danilo.aikotestebus.presentation.features.authenticator.AuthenticatorScreen
import br.com.danilo.aikotestebus.presentation.features.stopbyline.BusStopByLineScreen
import br.com.danilo.aikotestebus.presentation.features.stopbyline.BusStopMapScreen
import br.com.danilo.aikotestebus.presentation.util.ZERO
import br.com.danilo.aikotestebus.presentation.util.decodeLineDetailItem
import br.com.danilo.aikotestebus.presentation.util.decodeStopDetailItem
import com.google.android.gms.maps.model.LatLng

private const val LATITUTE_SP = "-23.5489"
private const val LONGITUDE_SP = "-46.6388"
private const val LATITUDE = "latitude"
private const val LONGITUDE = "longitude"
private const val ID_STOP = "idStop"
private const val ID_LINE = "idLine"
private const val ITEM = "item"
private const val PREFIX_BUS = "prefixBus"
private const val NAME_STOP = "nameStop"
private const val ADDRESS_STOP = "addressStop"

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
                navArgument(LATITUDE) {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = LATITUTE_SP
                },
                navArgument(LATITUDE) {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = LONGITUDE_SP
                }
            )
        ) { entry ->
            val latitude = entry.arguments?.getString(LATITUDE)?.toDoubleOrNull()
            val longitude = entry.arguments?.getString(LONGITUDE)?.toDoubleOrNull()
            val coord = LatLng(
                latitude ?: LATITUTE_SP.toDouble(),
                longitude ?: LONGITUDE_SP.toDouble()
            )

            TabScreen(coord, ZERO, navController)
        }

        composable(
            route = BusRoute.BusStopByLine.route,
            arguments = listOf(
                navArgument(ITEM) { type = NavType.StringType }
            )
        ) { entry ->
            val item = entry.arguments?.getString(ITEM)
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
                navArgument(ID_STOP) { type = NavType.StringType },
                navArgument(ID_LINE) { type = NavType.StringType },
                navArgument(ITEM) { type = NavType.StringType }
            )
        ) { entry ->
            val idStop = entry.arguments?.getString(ID_STOP)
            val idLine = entry.arguments?.getString(ID_LINE)
            val stopDetail = entry.arguments?.getString(ITEM)
            if (!idStop.isNullOrEmpty() && !idLine.isNullOrEmpty() && !stopDetail.isNullOrEmpty()) {
                ArrivalForecastScreen(
                    idStop.toIntOrNull().handleOptional(),
                    idLine.toIntOrNull().handleOptional(),
                    decodeStopDetailItem(stopDetail),
                    navController
                )
            }
        }

        composable(
            route = BusRoute.BusArrivalMap.route,
            arguments = listOf(
                navArgument(PREFIX_BUS) { type = NavType.StringType },
                navArgument(ID_STOP) { type = NavType.StringType },
                navArgument(ID_LINE) { type = NavType.StringType },
                navArgument(LATITUDE) { type = NavType.StringType },
                navArgument(LONGITUDE) { type = NavType.StringType },
            )
        ) { entry ->
            val idStop = entry.arguments?.getString(ID_STOP)?.toIntOrNull()
            val idLine = entry.arguments?.getString(ID_LINE)?.toIntOrNull()
            val prefixBus = entry.arguments?.getString(PREFIX_BUS)?.toIntOrNull()
            val latitude = entry.arguments?.getString(LATITUDE)?.toDoubleOrNull()
            val longitude = entry.arguments?.getString(LONGITUDE)?.toDoubleOrNull()

            val coord = LatLng(
                latitude ?: LATITUTE_SP.toDouble(),
                longitude ?: LONGITUDE_SP.toDouble()
            )

            ArrivalMapScreen(
                prefixBus = prefixBus.handleOptional(),
                idStop = idStop.handleOptional(),
                idLine = idLine.handleOptional(),
                initialLocation = coord,
                navController
            )
        }

        composable(
            route = BusRoute.BusStopMap.route,
            arguments = listOf(
                navArgument(NAME_STOP) { type = NavType.StringType },
                navArgument(ADDRESS_STOP) { type = NavType.StringType },
                navArgument(LATITUDE) { type = NavType.StringType },
                navArgument(LONGITUDE) { type = NavType.StringType },
            )
        ) { entry ->
            val nameStop = entry.arguments?.getString(NAME_STOP) ?: ""
            val addressStop = entry.arguments?.getString(ADDRESS_STOP) ?: ""
            val latitude = entry.arguments?.getString(LATITUDE)?.toDoubleOrNull()
            val longitude = entry.arguments?.getString(LONGITUDE)?.toDoubleOrNull()

            val stopLocation = LatLng(
                latitude ?: LATITUTE_SP.toDouble(),
                longitude ?: LONGITUDE_SP.toDouble()
            )

            BusStopMapScreen(
                stopLocation,
                nameStop,
                addressStop,
                navController
            )
        }
    }
}