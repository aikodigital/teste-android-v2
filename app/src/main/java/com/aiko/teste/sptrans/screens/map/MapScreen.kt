package com.aiko.teste.sptrans.screens.map

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.aiko.teste.sptrans.utils.composable.MapBoxMap
import com.mapbox.geojson.Point
import com.mapbox.maps.plugin.annotation.generated.PointAnnotation
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.BusStopScreenDestination
import com.ramcosta.composedestinations.generated.destinations.SearchScreenDestination
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

    MapScreenContent(centerLocation, busStops)

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

@Composable
fun MapScreenContent(centerLocation: Point?, busStops: List<Point>?) {
    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = "",
            onValueChange = { },
            placeholder = { Text(text = "Pesquise uma linha ou uma parada...") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        destinationsNavigator.navigate(SearchScreenDestination)
                    }
                },
        )

        MapBoxMap(
            centerPoint = centerLocation,
            busStops = busStops,
            handleBusStopClick = ::handlePointClick
        )
    }
}

private fun handlePointClick(pointAnnotation: PointAnnotation): Boolean {
    val busStop = mapScreenViewModel.getBusStopFromPoint(pointAnnotation.point)
    destinationsNavigator.popBackStack()
    destinationsNavigator.navigate(BusStopScreenDestination(busStop.stopCode, busStop.stopName))
    return true
}