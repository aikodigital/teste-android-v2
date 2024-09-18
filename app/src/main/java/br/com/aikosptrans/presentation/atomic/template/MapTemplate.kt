package br.com.aikosptrans.presentation.atomic.template

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.navigation.NavController
import br.com.aikosptrans.domain.entity.CityLocation
import br.com.aikosptrans.domain.entity.ClusterData
import br.com.aikosptrans.presentation.atomic.organism.BottomNavigationOrganism
import br.com.aikosptrans.presentation.atomic.organism.ClusterMapOrganism
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapTemplate(
    navController: NavController,
    items: List<ClusterData>,
    iconMarker: Painter,
    topBar: @Composable () -> Unit = {}
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(
                CityLocation.SAO_PAULO.latitude,
                CityLocation.SAO_PAULO.longitude
            ),
            ZOOM_LEVEL
        )
    }
    val uiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                compassEnabled = false,
                zoomControlsEnabled = false
            )
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = topBar,
        bottomBar = {
            BottomNavigationOrganism(navController)
        }
    ) { _ ->
        Column {
            GoogleMap(
                cameraPositionState = cameraPositionState,
                uiSettings = uiSettings,
            ) {
                ClusterMapOrganism(
                    items = items,
                    iconMarker = iconMarker
                )
            }
        }
    }
}

const val ZOOM_LEVEL = 18f