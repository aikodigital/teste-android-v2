package com.leonardolino.busfinder.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.leonardolino.busfinder.R
import com.leonardolino.busfinder.domain.model.VehiclePosition
import com.leonardolino.busfinder.presentation.components.ErrorMessage
import com.leonardolino.busfinder.presentation.components.LoadingIndicator
import com.leonardolino.busfinder.presentation.state.UiState
import com.leonardolino.busfinder.presentation.viewmodel.LineDetailsViewModel
import com.leonardolino.busfinder.utils.getBitmapDescriptorFromResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LineDetailsScreen(
    modifier: Modifier = Modifier,
    lineCode: Int,
    lineDirection: Int,
    fullSign: String,
    viewModel: LineDetailsViewModel,
    onBackClick: () -> Unit
) {
    val lineDetailsState by viewModel.state.collectAsState()
    val markerIcon = getBitmapDescriptorFromResource(
        R.drawable.directions_bus_24dp_e8eaed_fill1_wght400_grad0_opsz24,
        100, 100,
        tintColor = MaterialTheme.colorScheme.tertiary.toArgb()
    )

    LaunchedEffect(lineCode, lineDirection) {
        viewModel.loadLineDetails(lineCode, lineDirection, fullSign = fullSign)
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.clearState()
        }
    }

    val cameraPosition = rememberCameraPositionState()

    LaunchedEffect(lineDetailsState.vehiclePositions) {
        if (lineDetailsState.vehiclePositions is UiState.Success) {
            val boundsBuilder = LatLngBounds.builder()
            (lineDetailsState.vehiclePositions as UiState.Success<VehiclePosition>).data.vehicles.forEach { vehicle ->
                boundsBuilder.include(LatLng(vehicle.latitude, vehicle.longitude))
            }

            val bounds = boundsBuilder.build()
            cameraPosition.move(CameraUpdateFactory.newLatLngBounds(bounds, 100))
        }
    }

    Scaffold(topBar = {
        TopAppBar(title = {}, navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
            }
        })
    }) { paddingValues ->
        when (val vehiclePositions = lineDetailsState.vehiclePositions) {
            UiState.Loading -> {
                LoadingIndicator()
            }

            is UiState.Success -> {
                LazyColumn {
                    item {
                        GoogleMap(
                            modifier
                                .height(400.dp)
                                .padding(paddingValues),
                            cameraPositionState = cameraPosition,
                            uiSettings = MapUiSettings(
                                mapToolbarEnabled = false,
                                zoomControlsEnabled = false,
                                zoomGesturesEnabled = false,
                                tiltGesturesEnabled = false,
                                rotationGesturesEnabled = false
                            )
                        ) {
                            vehiclePositions.data.vehicles.forEach { vehicle ->
                                Marker(
                                    state = MarkerState(
                                        position = LatLng(
                                            vehicle.latitude, vehicle.longitude
                                        )
                                    ),
                                    title = vehicle.prefix,
                                    icon = markerIcon
                                )
                            }
                        }
                    }
                    item {
                        with(lineDetailsState.lineDetails as UiState.Success) {
                            data.first().let { line ->
                                Column(Modifier.padding(16.dp)) {
                                    Row(Modifier.padding(bottom = 24.dp)) {
                                        Box(
                                            Modifier
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(MaterialTheme.colorScheme.secondaryContainer)
                                                .padding(4.dp)
                                        ) {
                                            Text(text = line.signPrefix)
                                        }
                                        Spacer(modifier = Modifier.width(16.dp))
                                        Text(
                                            text = "${line.primarySign} / ${line.secondarySign}",
                                            style = MaterialTheme.typography.headlineSmall
                                        )
                                    }

                                    ListItem(headlineContent = { Text(text = stringResource(R.string.is_circular)) },
                                        trailingContent = { Text(text = line.isCircular.toString()) })

                                    ListItem(headlineContent = { Text(text = stringResource(R.string.vehicles_current_number)) },
                                        trailingContent = { Text(text = vehiclePositions.data.vehicles.size.toString()) })
                                }
                            }
                        }
                    }
                }
            }

            is UiState.Error -> {
                ErrorMessage(message = vehiclePositions.message)
            }
        }
    }
}


