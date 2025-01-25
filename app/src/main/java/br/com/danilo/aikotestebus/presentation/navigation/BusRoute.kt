package br.com.danilo.aikotestebus.presentation.navigation

sealed class BusRoute(val route: String) {

    data object BusSplash : BusRoute(route = "bus_splash")

    data object BusLineDetails : BusRoute(route = "bus_line_details")

}