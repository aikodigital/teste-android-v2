package hopeapps.dedev.sptrans.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import hopeapps.dedev.sptrans.presentation.line_bus.LineBusRoot
import hopeapps.dedev.sptrans.presentation.line_bus.LineBusViewModel
import hopeapps.dedev.sptrans.presentation.search.SearchScreenRoot
import hopeapps.dedev.sptrans.utils.JsonHelper.decodeJson
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
                    navController.navigate(Routes.BusLineDetails.route.replace(
                        "{BUS_LINE_ITEM}", busLine.encodeJson()
                    ))
                }
            )
        }

        composable(
            route = Routes.BusLineDetails.route,
            arguments = listOf(
                navArgument("BUS_LINE_ITEM") { type = NavType.StringType },
            )
        ) { entry ->
            val busLineJson = entry.arguments?.getString("BUS_LINE_ITEM")
            val viewModel =  koinViewModel<LineBusViewModel>()


            LaunchedEffect(busLineJson) {
                busLineJson?.decodeJson()?.let { busLine ->
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
    }
}

