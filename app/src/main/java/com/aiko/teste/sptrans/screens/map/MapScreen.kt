package com.aiko.teste.sptrans.screens.map

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.aiko.teste.sptrans.utils.composable.MapBoxMap
import com.mapbox.geojson.Point
import com.mapbox.maps.plugin.annotation.generated.PointAnnotation
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.BusStopScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

private lateinit var mapScreenViewModel: MapScreenViewModel
private lateinit var destinationsNavigator: DestinationsNavigator

@SuppressLint("MissingPermission")
@Destination<RootGraph>(start = true)
@Composable
fun MapScreen(
    navigator: DestinationsNavigator,
    viewModel: MapScreenViewModel = hiltViewModel<MapScreenViewModel>()
) {
    mapScreenViewModel = viewModel
    destinationsNavigator = navigator

    var centerLocation: Point? by remember {
        mutableStateOf(null)
    }
    var busStops: List<Point>? by remember {
        mutableStateOf(null)
    }

    MapBoxMap(
        centerPoint = centerLocation,
        busStops = busStops,
        handlePointClick = ::handlePointClick
    )
    viewModel.getMapPoints()

    val centerLocationObserver = Observer<Point> { point ->
        centerLocation = point
    }
    viewModel.centerLocation.observe(LocalLifecycleOwner.current, centerLocationObserver)

    val busStopsObserver = Observer<List<Point>> { result ->
        busStops = result
    }
    viewModel.busStopsPoints.observe(LocalLifecycleOwner.current, busStopsObserver)
}

fun handlePointClick(pointAnnotation: PointAnnotation): Boolean {
    val busStop = mapScreenViewModel.getBusStopFromPoint(pointAnnotation.point)
    destinationsNavigator.popBackStack()
    destinationsNavigator.navigate(BusStopScreenDestination(busStop.stopCode))
    return true
}