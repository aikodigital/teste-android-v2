package hopeapps.dedev.sptrans.presentation.screens.maps

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.algo.NonHierarchicalViewBasedAlgorithm
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.clustering.Clustering
import com.google.maps.android.compose.clustering.rememberClusterManager
import com.google.maps.android.compose.clustering.rememberClusterRenderer
import com.google.maps.android.compose.rememberCameraPositionState
import hopeapps.dedev.sptrans.R
import hopeapps.dedev.sptrans.domain.models.DynamicPoint
import hopeapps.dedev.sptrans.domain.models.Location
import hopeapps.dedev.sptrans.domain.models.StaticPoint

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun TrackerMap(
    modifier: Modifier = Modifier,
    currentFocus: Location?,
    busStopLocation: StaticPoint?,
    dynamicPoints: List<DynamicPoint>,
    openBottomSheetDialog: (dynamicPoint: DynamicPoint) -> Unit
) {
    val cameraPositionState = rememberCameraPositionState()
    val context = LocalContext.current
    val mapStyle = remember { MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style) }
    val mapStyleLight = remember { MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style_light) }

    LaunchedEffect(currentFocus) {
        if (currentFocus != null) {
            val latLng = LatLng(currentFocus.lat, currentFocus.long)
            cameraPositionState.animate(
                CameraUpdateFactory.newLatLngZoom(latLng, 18f)
            )
        }
    }

    GoogleMap(
        cameraPositionState = cameraPositionState,
        properties = MapProperties(
            mapStyleOptions = if (isSystemInDarkTheme()) mapStyle else null
        ),
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false,
            compassEnabled = true,
            rotationGesturesEnabled = true,
            scrollGesturesEnabled = true,
            tiltGesturesEnabled = false,
            zoomGesturesEnabled = true,
            scrollGesturesEnabledDuringRotateOrZoom = false
        ),
        modifier = Modifier
    ) {

        val clusterItem = rememberClusterManager<DynamicPoint>()
        val nonHierarchicalDistanceBasedAlgorithm =
            NonHierarchicalViewBasedAlgorithm<DynamicPoint>(
                LocalConfiguration.current.screenHeightDp,
                LocalConfiguration.current.screenWidthDp
            )

        clusterItem?.let { manager ->
            val renderer = rememberClusterRenderer(
                clusterContent = { cluster ->
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.bus_icon),
                            contentDescription = "Cluster de ônibus",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(24.dp)
                        )

                        Text(
                            text = cluster.size.toString(),
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .background(Color.Black.copy(alpha = 0.6f), shape = CircleShape)
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                },
                clusterItemContent = {
                    Box(
                        modifier = Modifier
                            .size(35.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.bus_icon),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                clusterManager = manager
            )
            clusterItem.algorithm = nonHierarchicalDistanceBasedAlgorithm
            SideEffect {
                if (manager.renderer != renderer) {
                    manager.renderer = renderer ?: return@SideEffect
                }
            }
            ApplyClicks(
                clusterManager = manager,
                openBottomSheetDialog = {
                    openBottomSheetDialog.invoke(it)
                }
            )
            Clustering(
                items = dynamicPoints,
                clusterManager = clusterItem
            )
        }

        busStopLocation?.let {
            MarkerComposable(
                state = MarkerState(position = LatLng(it.latitude, it.longitude))
            ) {
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.bus_stop_icon),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ApplyClicks(
    clusterManager: ClusterManager<DynamicPoint>,
    openBottomSheetDialog: (busLine: DynamicPoint) -> Unit
) {
    SideEffect {
        clusterManager.setOnClusterItemClickListener { clusterBusLine ->
            openBottomSheetDialog(clusterBusLine)
            false
        }
    }
}