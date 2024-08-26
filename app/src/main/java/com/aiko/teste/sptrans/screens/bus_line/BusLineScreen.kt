package com.aiko.teste.sptrans.screens.bus_line

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.aiko.teste.sptrans.data.objects.BusLine
import com.aiko.teste.sptrans.data.objects.BusPosition
import com.aiko.teste.sptrans.utils.composable.BackHandler
import com.aiko.teste.sptrans.utils.composable.MapBoxMap
import com.mapbox.geojson.Point
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination<RootGraph>
@Composable
fun BusLineScreen(
    navigator: DestinationsNavigator,
    busLine: BusLine,
    viewModel: BusLineScreenViewModel = hiltViewModel<BusLineScreenViewModel>()
) {
    var busStops: List<Point>? by remember {
        mutableStateOf(null)
    }
    var busses: List<BusPosition>? by remember {
        mutableStateOf(null)
    }
    var centerLocation: Point? by remember {
        mutableStateOf(null)
    }

    MapBoxMap(centerPoint = centerLocation, busStops = busStops, busses = busses)
    BackHandler(navigator = navigator)

    viewModel.getCenterLocation()
    viewModel.getBusStops(busLine.lineCode)
    viewModel.getBusPositions(busLine.lineCode)

    val bussesObserver = Observer<List<BusPosition>> { result -> busses = result }
    val busStopsObserver = Observer<List<Point>> { result -> busStops = result }
    val centerLocationObserver = Observer<Point> { point -> centerLocation = point }
    viewModel.centerLocation.observe(LocalLifecycleOwner.current, centerLocationObserver)
    viewModel.busPositions.observe(LocalLifecycleOwner.current, bussesObserver)
    viewModel.busStops.observe(LocalLifecycleOwner.current, busStopsObserver)
}