package br.com.danilo.aikotestebus.presentation.features.maplocation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import br.com.danilo.aikotestebus.R
import br.com.danilo.aikotestebus.domain.mapper.mapBusesToMapMarkers
import br.com.danilo.aikotestebus.presentation.components.ClusteringMap
import br.com.danilo.aikotestebus.presentation.util.state.MapLocationBusState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import org.koin.androidx.compose.koinViewModel

@Composable
fun MapLocationBusScreen(
    initialCoord: LatLng,
    isTabVisible: Boolean,
    mapLocationBusViewModel: MapLocationBusViewModel = koinViewModel()
) {
    DisposableEffect(isTabVisible) {
        if (isTabVisible) {
            mapLocationBusViewModel.startPeriodicTask()
        } else {
            mapLocationBusViewModel.stopPeriodicTask()
        }

        onDispose {
            mapLocationBusViewModel.stopPeriodicTask()
        }
    }

    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initialCoord, 18f)
    }

    val uiState by mapLocationBusViewModel.uiState.collectAsState()

    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                compassEnabled = false,
                zoomControlsEnabled = false,
                mapToolbarEnabled = true
            )
        )
    }

    val mapProperties by remember {
        mutableStateOf(
            MapProperties(maxZoomPreference = 20f, minZoomPreference = 13f)
        )
    }

    Box(Modifier.fillMaxSize()) {
        GoogleMap(
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            uiSettings = mapUiSettings
        ) {
            when (uiState) {
                is MapLocationBusState.Success -> {
                    val data = (uiState as MapLocationBusState.Success).items

                    ClusteringMap(
                        clusterItems = mapBusesToMapMarkers(data),
                        markerIcon = painterResource(R.drawable.ic_bus),
                    )
                }

                else -> {}
            }
        }
    }
}