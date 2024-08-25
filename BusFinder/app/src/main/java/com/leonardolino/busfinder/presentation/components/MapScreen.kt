package com.leonardolino.busfinder.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.leonardolino.busfinder.presentation.state.UiState
import com.leonardolino.busfinder.presentation.viewmodel.MapViewModel

@Composable
fun GoogleMapScreen(
    modifier: Modifier = Modifier,
    viewModel: MapViewModel = hiltViewModel()
) {
    val busStopListState by viewModel.busStopsListState.collectAsState()
    val arrivalsState by viewModel.arrivalsState.collectAsState()
    val shouldShowBottomSheet by viewModel.isSheetVisible.collectAsState()

    val cameraPosition = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(-23.5543809, -46.6604199), 12f)
    }

    Scaffold { paddingValues ->
        when (val state = busStopListState) {
            is UiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is UiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = state.message, textAlign = TextAlign.Center)
                }
            }

            is UiState.Success -> {
                GoogleMap(
                    modifier = modifier.padding(paddingValues),
                    cameraPositionState = cameraPosition
                ) {
                    for (stop in state.data) {
                        val markerState =
                            remember { MarkerState(LatLng(stop.latitude, stop.longitude)) }
                        Marker(
                            state = markerState,
                            title = stop.name,
                            snippet = stop.address,
                            onInfoWindowClick = {
                                viewModel.onInfoWindowClick(stop.code)
                            },
                        )
                    }
                }

                if (shouldShowBottomSheet) {
                    ArrivalsBottomSheet(
                        onDismissRequest = { viewModel.hideBottomSheet() },
                        contentState = arrivalsState,
                    )
                }
            }
        }
    }
}