package com.aiko.teste.sptrans.screens.bus_stop_tracking

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.aiko.teste.sptrans.data.objects.BusPosition
import com.aiko.teste.sptrans.data.objects.Line
import com.aiko.teste.sptrans.utils.composable.MapBoxMap
import com.mapbox.geojson.Point
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.BusStopTrackingScreenDestination

@Destination<RootGraph>(navArgs = BusStopTrackingScreenArgs::class)
@Composable
fun BusStopTrackingScreen(
    navBackStackEntry: NavBackStackEntry,
    viewModel: BusStopTrackingViewModel = hiltViewModel<BusStopTrackingViewModel>()
) {
    val navArgs = BusStopTrackingScreenDestination.argsFrom(navBackStackEntry)
    val busStop = viewModel.getBusStop(navArgs.busStopCode)
    val busLines = navArgs.busStopLines

    val busStopPoint = Point.fromLngLat(busStop.longitude, busStop.latitude)
    val busses: List<BusPosition> = busLines.flatMap { busLine ->
        busLine.positions.map { it }
    }

    MapBoxMap(
        centerPoint = busStopPoint,
        busStops = listOf(busStopPoint),
        busses = busses
    )
}

data class BusStopTrackingScreenArgs(
    val busStopCode: String,
    val busStopLines: Array<Line>
)