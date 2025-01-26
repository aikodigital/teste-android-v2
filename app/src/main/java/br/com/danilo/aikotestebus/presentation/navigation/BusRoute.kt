package br.com.danilo.aikotestebus.presentation.navigation

sealed class BusRoute(val route: String) {

    data object BusAuthenticator : BusRoute(route = "bus_authenticator")

    data object BusTabContainer : BusRoute(route = "bus_tab_container/{latitude}/{longitude}")

    data object BusArrivalForecastTime : BusRoute(route = "bus_arrival_forecast/{idStop}/{idLine}/{item}")

    data object BusArrivalMap : BusRoute(route = "bus_arrival_map/{prefixBus}/{idStop}/{idLine}/{latitude}/{longitude}")

    data object BusStopByLine : BusRoute(route = "bus_stop_by_line/{item}")

    data object BusStopMap : BusRoute(route = "bus_stop_map/{nameStop}/{addressStop}/{latitude}/{longitude}")

}