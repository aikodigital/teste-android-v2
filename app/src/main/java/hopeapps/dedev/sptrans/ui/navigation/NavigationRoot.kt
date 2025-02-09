package hopeapps.dedev.sptrans.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import hopeapps.dedev.sptrans.domain.models.ActionPoint
import hopeapps.dedev.sptrans.domain.models.BusStop
import hopeapps.dedev.sptrans.presentation.bus_stop.BusStopDetailsRoot
import hopeapps.dedev.sptrans.presentation.bus_stop.BusStopViewModel
import hopeapps.dedev.sptrans.presentation.line_bus.LineBusRoot
import hopeapps.dedev.sptrans.presentation.line_bus.LineBusViewModel
import hopeapps.dedev.sptrans.presentation.maps.MapsOverviewScreenRoot
import hopeapps.dedev.sptrans.presentation.maps.OverviewMapsViewModel
import hopeapps.dedev.sptrans.presentation.search.SearchScreenRoot
import hopeapps.dedev.sptrans.utils.JsonHelper.decodeBusLineJson
import hopeapps.dedev.sptrans.utils.JsonHelper.decodeBusStopJson
import hopeapps.dedev.sptrans.utils.JsonHelper.decodeFromUrl
import hopeapps.dedev.sptrans.utils.JsonHelper.decodeMapPoints
import hopeapps.dedev.sptrans.utils.JsonHelper.encodeJson
import hopeapps.dedev.sptrans.utils.JsonHelper.encodeToUrl
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
                    navController.navigate(Routes.BusLineDetails.route.replace(
                        "{busLineJson}", busLine.encodeJson().encodeToUrl()
                    ))
                },
                onItemBusStopClick = { busStop: BusStop ->
                    navController.navigate(Routes.BusStopDetails.route.replace(
                        "{busStopJson}", busStop.encodeJson().encodeToUrl()
                    ))
                },
                navigateToMaps = {
                    val json = listOf(ActionPoint).encodeJson()
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
            val viewModel =  koinViewModel<LineBusViewModel>()


            LaunchedEffect(busLineJson) {
                busLineJson?.decodeFromUrl()?.decodeBusLineJson()?.let { busLine ->
                    viewModel.load(busLine = busLine)
                }
            }

            viewModel.state
            LineBusRoot(
                viewModel = viewModel,
                viewInMapClick = {
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
            val viewModel =  koinViewModel<BusStopViewModel>()

            LaunchedEffect(busStopJson) {
                busStopJson?.decodeFromUrl()?.decodeBusStopJson()?.let { busStop ->
                    viewModel.load(busStop = busStop)
                }
            }

            BusStopDetailsRoot(
                viewModel = viewModel,
                viewInMapClick = { staticPoint ->
                    val json = listOf(staticPoint).encodeJson()
                    navController.navigate("maps/${json}")
                }
            )
        }

        composable(
            route = Routes.Maps.route,
            arguments = listOf(
                navArgument("mapsPoint") { type = NavType.StringType }
            )
        ) { entry ->
            val mapsPoints = entry.arguments?.getString("mapsPoint")
            val viewModel =  koinViewModel<OverviewMapsViewModel>()

            LaunchedEffect(mapsPoints) {
                mapsPoints?.decodeMapPoints()?.let { mapsPoints ->
                    viewModel.load(mapsPoints)
                }
            }
            MapsOverviewScreenRoot()
        }
    }
}

