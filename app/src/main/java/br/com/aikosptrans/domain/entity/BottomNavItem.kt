package br.com.aikosptrans.domain.entity

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import br.com.aikosptrans.R
import br.com.aikosptrans.presentation.navigation.AppDestination

sealed class BottomNavItem(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val label: Int
) {
    data object BusMap : BottomNavItem(
        route = AppDestination.BusMap.createRoute(),
        icon = R.drawable.ic_bus,
        label = R.string.bottom_nav_bus
    )
    data object StopBusMap : BottomNavItem(
        route = AppDestination.StopBusMap.createRoute(),
        icon = R.drawable.ic_stop_bus,
        label = R.string.bottom_nav_stop_bus
    )
    data object BusLine : BottomNavItem(
        route = AppDestination.BusLine.createRoute(),
        icon = R.drawable.ic_bus_line,
        label = R.string.bottom_nav_bus_line
    );

    companion object {
        fun all() = listOf(
            BusMap,
            StopBusMap,
            BusLine
        )
    }
}