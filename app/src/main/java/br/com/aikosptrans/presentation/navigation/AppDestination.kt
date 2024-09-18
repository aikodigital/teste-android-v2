package br.com.aikosptrans.presentation.navigation

import br.com.aikosptrans.util.encodeKObjectInString

sealed class AppDestination(val route: String) {
    abstract fun createRoute(): String

    data object Splash : AppDestination("SPLASH_SCREEN") {
        override fun createRoute(): String = route
    }

    data object BusMap : AppDestination("BUS_MAP_SCREEN") {
        override fun createRoute(): String = route
    }

    data object StopBusMap : AppDestination("STOP_BUS_MAP_SCREEN") {
        override fun createRoute(): String = route
    }

    data object BusLine : AppDestination("BUS_LINE_SCREEN") {
        override fun createRoute(): String = route
    }

    data class BusLineDetail(
        val busLine: br.com.aikosptrans.domain.entity.BusLine? = null
    ) : AppDestination("BUS_LINE_DETAIL_SCREEN") {
        override fun createRoute(): String = "$route/${busLine.encodeKObjectInString()}"
    }
}