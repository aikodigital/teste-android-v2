package com.aiko.teste.sptrans.screens.map

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.aiko.teste.sptrans.utils.composable.MapBoxMap
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mapbox.geojson.Point
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

private val tag = "MapScreenComposable"

@SuppressLint("MissingPermission")
@Destination<RootGraph>(start = true)
@Composable
fun MapScreen(
    navigator: DestinationsNavigator,
    viewModel: MapScreenViewModel = hiltViewModel<MapScreenViewModel>()
) {
    var centerLocation: Point? by remember {
        mutableStateOf(null)
    }

    MapBoxMap(centerPoint = centerLocation)
    viewModel.getCenterLocation()
    val centerLocationAsState by viewModel.centerLocation.observeAsState()
}