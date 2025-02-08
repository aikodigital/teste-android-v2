package hopeapps.dedev.sptrans.ui.navigation

sealed class Routes(val route: String) {
    data object Search: Routes(route = "search")
    data object BusLineDetails: Routes(route = "bus_line_details/{BUS_LINE_ITEM}")
}