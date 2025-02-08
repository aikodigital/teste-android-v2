package hopeapps.dedev.sptrans.ui.navigation

sealed class Routes(val route: String) {
    data object Search: Routes(route = "search")
    data object BusLineDetails: Routes(route = "bus_line_details/{busLineJson}")
    data object BusStopDetails: Routes(route = "bus_stop_details/{busStopJson}")
}