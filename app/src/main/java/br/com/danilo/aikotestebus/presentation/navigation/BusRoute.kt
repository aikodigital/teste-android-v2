package br.com.danilo.aikotestebus.presentation.navigation

sealed class BusRoute(val route: String) {

    data object BusAuthenticator : BusRoute(route = "bus_authenticator")

    data object BusTabContainer : BusRoute(route = "bus_tab_container/{latitude}/{longitude}")

    data object BusArrivalForecastTime : BusRoute(route = "bus_arrival_forecast/{idStop}/{idLine}")

    data object BusStopByLine : BusRoute(route = "bus_stop_by_line/{item}")

}