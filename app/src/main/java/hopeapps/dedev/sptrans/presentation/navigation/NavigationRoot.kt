package hopeapps.dedev.sptrans.presentation.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import hopeapps.dedev.sptrans.domain.models.ActionPoint
import hopeapps.dedev.sptrans.domain.models.BusStop
import hopeapps.dedev.sptrans.presentation.screens.bus_stop.BusStopDetailsRoot
import hopeapps.dedev.sptrans.presentation.screens.bus_stop.BusStopViewModel
import hopeapps.dedev.sptrans.presentation.screens.line_bus.LineBusRoot
import hopeapps.dedev.sptrans.presentation.screens.line_bus.LineBusViewModel
import hopeapps.dedev.sptrans.presentation.screens.maps.MapsOverviewScreenRoot
import hopeapps.dedev.sptrans.presentation.screens.maps.OverviewMapsViewModel
import hopeapps.dedev.sptrans.presentation.screens.search.SearchScreenRoot
import hopeapps.dedev.sptrans.utils.JsonHelper.decodeBusLineJson
import hopeapps.dedev.sptrans.utils.JsonHelper.decodeBusStopJson
import hopeapps.dedev.sptrans.utils.JsonHelper.decodeMapPoint
import hopeapps.dedev.sptrans.utils.JsonHelper.encodeJson
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Search.route
    ) {

        composable(
            route = Routes.Search.route
        ) {
            SearchScreenRoot(
                onItemBusLineClick = { busLine ->
                    navController.navigate("bus_line_details/${busLine.encodeJson()}")
                },
                onItemBusStopClick = { busStop: BusStop ->
                    navController.navigate("bus_stop_details/${busStop.encodeJson()}")
                },
                navigateToMaps = {
                    val json = ActionPoint.encodeJson()
                    navController.navigate("maps/${json}")
                }
            )
        }

        composable(
            route = Routes.BusLineDetails.route,
            arguments = listOf(
                navArgument("busLineJson") { type = NavType.StringType },
            )
        ) { entry ->
            val busLineJson = entry.arguments?.getString("busLineJson")
            val viewModel = koinViewModel<LineBusViewModel>()


            LaunchedEffect(busLineJson) {
                busLineJson?.decodeBusLineJson()?.let { busLine ->
                    viewModel.load(busLine = busLine)
                }
            }

            LineBusRoot(
                viewModel = viewModel,
                viewInMapClick = {
                },
                navigateBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Routes.BusStopDetails.route,
            arguments = listOf(
                navArgument("busStopJson") { type = NavType.StringType },
            )
        ) { entry ->
            val busStopJson = entry.arguments?.getString("busStopJson")
            val viewModel = koinViewModel<BusStopViewModel>()

            LaunchedEffect(busStopJson) {
                busStopJson?.decodeBusStopJson()?.let { busStop ->
                    viewModel.load(busStop = busStop)
                }
            }

            BusStopDetailsRoot(
                viewModel = viewModel,
                viewInMapClick = { staticPoint ->
                    val json = staticPoint.encodeJson()
                    navController.navigate("maps/${json}")
                },
                viewBusInMap = { dynamicPoint ->
                    val json = dynamicPoint.encodeJson()
                    navController.navigate("maps/${json}")
                },
                navigateToBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Routes.Maps.route,
            enterTransition = { slideInHorizontally() },
            exitTransition = { slideOutHorizontally() },
            arguments = listOf(
                navArgument("mapsPoint") { type = NavType.StringType }
            )
        ) { entry ->
            val mapsPoints = entry.arguments?.getString("mapsPoint")
            val viewModel = koinViewModel<OverviewMapsViewModel>()

            LaunchedEffect(mapsPoints) {
                mapsPoints?.decodeMapPoint()?.let { mapPoint ->
                    viewModel.load(mapPoint)
                }
            }
            MapsOverviewScreenRoot(
                navigateToBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

