package br.com.danilo.aikotestebus.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.danilo.aikotestebus.presentation.features.TabScreen
import br.com.danilo.aikotestebus.presentation.features.authenticator.AuthenticatorScreen
import br.com.danilo.aikotestebus.presentation.features.stopbyline.BusStopByLineScreen
import br.com.danilo.aikotestebus.presentation.util.decodeLineDetailItem

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

        composable(route = BusRoute.BusTabContainer.route) {
            TabScreen(navController)
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
    }
}