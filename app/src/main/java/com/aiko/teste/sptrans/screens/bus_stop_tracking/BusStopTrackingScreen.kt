package com.aiko.teste.sptrans.screens.bus_stop_tracking

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavBackStackEntry
import com.aiko.teste.sptrans.data.objects.BusPosition
import com.aiko.teste.sptrans.data.objects.BusLine
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

    var centerLocation: Point? by remember {
        mutableStateOf(null)
    }
    val centerLocationObserver = Observer<Point> { point ->
        centerLocation = point
    }
    viewModel.centerLocation.observe(LocalLifecycleOwner.current, centerLocationObserver)
    viewModel.getCenterLocation()

    MapBoxMap(
        centerPoint = centerLocation,
        busStops = listOf(busStopPoint),
        busses = busses
    )
}

data class BusStopTrackingScreenArgs(
    val busStopCode: String,
    val busStopLines: Array<BusLine>
)