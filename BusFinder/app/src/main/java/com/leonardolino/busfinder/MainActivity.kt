package com.leonardolino.busfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.leonardolino.busfinder.presentation.viewmodel.MapScreenViewModel
import com.leonardolino.busfinder.ui.theme.BusFinderTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusFinderTheme {
                val viewmodel: MapScreenViewModel = hiltViewModel()
                MapScreen(viewmodel)
            }
        }
    }
}

@Composable
fun MapScreen(viewModel: MapScreenViewModel) {
    val state = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(-23.5543809, -46.6604199), 12f)
    }
    val stops = viewModel.busStopsData.collectAsState().value
    val coroutineScope = rememberCoroutineScope()

    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                mapToolbarEnabled = false,
                rotationGesturesEnabled = false
            )
        )
    }

    GoogleMap(
        uiSettings = mapUiSettings,
        cameraPositionState = state,
        onMapLoaded = {
            coroutineScope.launch {
                viewModel.fetchBusStops()
            }
        },
    ) {
        for (stop in stops) {
            val markerState = remember { MarkerState(LatLng(stop.latitude, stop.longitude)) }
            Marker(
                state = markerState,
                title = stop.name,
                snippet = stop.address
            )
        }
    }
}