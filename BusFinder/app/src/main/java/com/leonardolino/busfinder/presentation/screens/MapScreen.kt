package com.leonardolino.busfinder.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.leonardolino.busfinder.R
import com.leonardolino.busfinder.domain.model.EstimatedArrival
import com.leonardolino.busfinder.presentation.components.ArrivalsBottomSheet
import com.leonardolino.busfinder.presentation.components.ErrorMessage
import com.leonardolino.busfinder.presentation.components.LoadingIndicator
import com.leonardolino.busfinder.presentation.state.UiState
import com.leonardolino.busfinder.presentation.viewmodel.MapViewModel
import com.leonardolino.busfinder.utils.getBitmapDescriptorFromResource

@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    viewModel: MapViewModel,
    onListItemClick: (EstimatedArrival.Stop.Line) -> Unit
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
                LoadingIndicator()
            }

            is UiState.Error -> {
                ErrorMessage(message = state.message)
            }

            is UiState.Success -> {
                GoogleMap(
                    modifier = modifier.padding(paddingValues),
                    cameraPositionState = cameraPosition,
                    uiSettings = MapUiSettings(
                        mapToolbarEnabled = false,
                        zoomControlsEnabled = false,
                        tiltGesturesEnabled = false
                    )
                ) {
                    val markerIcon = getBitmapDescriptorFromResource(
                        R.drawable.flag_24dp_e8eaed_fill1_wght400_grad0_opsz24,
                        100, 100,
                        tintColor = MaterialTheme.colorScheme.tertiary.toArgb()
                    )
                    for (stop in state.data) {
                        val markerState =
                            remember { MarkerState(LatLng(stop.latitude, stop.longitude)) }
                        Marker(
                            state = markerState,
                            title = stop.name,
                            snippet = stop.address,
                            icon = markerIcon,
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
                        onListItemClick = onListItemClick
                    )
                }
            }
        }
    }
}