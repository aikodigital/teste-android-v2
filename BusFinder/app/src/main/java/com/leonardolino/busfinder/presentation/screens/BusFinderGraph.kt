package com.leonardolino.busfinder.presentation.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.leonardolino.busfinder.presentation.screens.Constants.FULL_SIGN
import com.leonardolino.busfinder.presentation.screens.Constants.LINE_CODE
import com.leonardolino.busfinder.presentation.screens.Constants.LINE_DIRECTION
import com.leonardolino.busfinder.presentation.viewmodel.LineDetailsViewModel
import com.leonardolino.busfinder.presentation.viewmodel.MapViewModel

private object Constants {
    const val LINE_CODE = "lineCode"
    const val LINE_DIRECTION = "lineDirection"
    const val FULL_SIGN = "fullSign"
}

enum class Routes(name: String) {
    MAP_SCREEN("map_screen"), LINE_DETAILS("line_details")
}

@Composable
fun BusFinderGraph(
    navController: NavHostController = rememberNavController(),
    mapViewModel: MapViewModel = hiltViewModel(),
    lineViewModel: LineDetailsViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController, startDestination = Routes.MAP_SCREEN.name
    ) {
        composable(Routes.MAP_SCREEN.name) {
            MapScreen(viewModel = mapViewModel, onListItemClick = { line ->
                navController.navigate("${Routes.LINE_DETAILS.name}/${line.code}/${line.operationDirection}/${line.fullSign}")
            })
        }
        composable(
            route = "${Routes.LINE_DETAILS.name}/{${LINE_CODE}}/{${LINE_DIRECTION}}/{${FULL_SIGN}}",
            arguments = listOf(navArgument(LINE_CODE) { type = NavType.IntType },
                navArgument(LINE_DIRECTION) { type = NavType.IntType },
                navArgument(FULL_SIGN) { type = NavType.StringType })
        ) { backStackEntry ->
            val lineCode = backStackEntry.arguments?.getInt(LINE_CODE) ?: return@composable
            val lineDirection =
                backStackEntry.arguments?.getInt(LINE_DIRECTION) ?: return@composable
            val fullSign = backStackEntry.arguments?.getString(FULL_SIGN) ?: return@composable
            LineDetailsScreen(
                lineCode = lineCode,
                lineDirection = lineDirection,
                fullSign = fullSign,
                onBackClick = {
                    navController.popBackStack()
                },
                viewModel = lineViewModel
            )
        }
    }
}

