package hopeapps.dedev.sptrans.presentation.maps

import android.graphics.Bitmap
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
import com.google.maps.android.compose.clustering.Clustering
import com.google.maps.android.compose.clustering.rememberClusterManager
import com.google.maps.android.compose.clustering.rememberClusterRenderer
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import hopeapps.dedev.sptrans.R
import hopeapps.dedev.sptrans.domain.models.Location
import hopeapps.dedev.sptrans.presentation.maps.cluster.ClusterBusLine
import kotlinx.coroutines.Job

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun TrackerMap(
    isRunFinished: Boolean,
    currentLocation: Location?,
    locations: List<ClusterBusLine>,
    onSnapshot: (Bitmap) -> Unit,
    modifier: Modifier = Modifier,
    openBottomSheetDialog: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current
    val mapStyle = remember {
        MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style)
    }
    val cameraPositionState = rememberCameraPositionState()
    val markerState = rememberMarkerState()

    val markerPositionLat by animateFloatAsState(
        targetValue = currentLocation?.lat?.toFloat() ?: 0f,
        animationSpec = tween(durationMillis = 500),
        label = ""
    )
    val markerPositionLong by animateFloatAsState(
        targetValue = currentLocation?.long?.toFloat() ?: 0f,
        animationSpec = tween(durationMillis = 500),
        label = ""
    )
    val markerPosition = remember(markerPositionLat, markerPositionLong) {
        LatLng(markerPositionLat.toDouble(), markerPositionLong.toDouble())
    }

    LaunchedEffect(markerPosition, isRunFinished) {
        if (!isRunFinished) {
            markerState.position = markerPosition
        }
    }

    LaunchedEffect(currentLocation, isRunFinished) {
        if (currentLocation != null && !isRunFinished) {
            val latLng = LatLng(currentLocation.lat, currentLocation.long)
            cameraPositionState.animate(
                CameraUpdateFactory.newLatLngZoom(latLng, 17f)
            )
        }
    }

    var triggerCapture by remember {
        mutableStateOf(false)
    }
    var createSnapshotJob: Job? = remember {
        null
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
        if (currentLocation != null) {
            MarkerComposable(
                currentLocation,
                state = markerState
            ) {
                Box(
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.map_24),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
        val clusterBusLine = rememberClusterManager<ClusterBusLine>()
//        val clusterStopBus = rememberClusterManager<ClusterStopBusLine>()

        val nonHierarchicalDistanceBasedAlgorithm =
            NonHierarchicalViewBasedAlgorithm<ClusterBusLine>(
                LocalConfiguration.current.screenHeightDp,
                LocalConfiguration.current.screenWidthDp
            )

        clusterBusLine?.let { manager ->
            val renderer = rememberClusterRenderer(
                clusterContent = {
                    Box(
                        modifier = Modifier
                            .size(35.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.map_24),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(20.dp)
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
                            painter = painterResource(R.drawable.map_24),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                },
                clusterManager = manager
            )
            clusterBusLine.algorithm = nonHierarchicalDistanceBasedAlgorithm
            SideEffect {
                if (manager.renderer != renderer) {
                    manager.renderer = renderer ?: return@SideEffect
                }
            }
            ApplyClicks(
                clusterManager = manager,
                openBottomSheetDialog = {
                    openBottomSheetDialog.invoke()
                }
            )
            Clustering(
                items = locations,
                clusterManager = clusterBusLine
            )
        }

//        val nonHierarchicalDistanceBasedAlgorithmStopBusLines =
//            NonHierarchicalViewBasedAlgorithm<ClusterStopBusLine>(
//                LocalConfiguration.current.screenHeightDp,
//                LocalConfiguration.current.screenWidthDp
//            )

//        clusterStopBus?.let { managerStopBusLine ->
//            val renderer = rememberClusterRenderer(
//                clusterContent = {
//                    Box(
//                        modifier = Modifier
//                            .size(35.dp)
//                            .clip(CircleShape)
//                            .background(MaterialTheme.colorScheme.primary),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Icon(
//                            imageVector = TestIcon,
//                            contentDescription = null,
//                            tint = MaterialTheme.colorScheme.onPrimary,
//                            modifier = Modifier.size(20.dp)
//                        )
//                    }
//                },
//                clusterItemContent = {
//                    Box(
//                        modifier = Modifier
//                            .size(35.dp)
//                            .clip(CircleShape)
//                            .background(MaterialTheme.colorScheme.primary),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Icon(
//                            imageVector = TestIcon,
//                            contentDescription = null,
//                            tint = MaterialTheme.colorScheme.onPrimary,
//                            modifier = Modifier.size(20.dp)
//                        )
//                    }
//                },
//                clusterManager = managerStopBusLine
//            )
//            clusterStopBus.algorithm = nonHierarchicalDistanceBasedAlgorithmStopBusLines
//            SideEffect {
//                if (managerStopBusLine.renderer != renderer) {
//                    managerStopBusLine.renderer = renderer ?: return@SideEffect
//                }
//            }
//            ApplyClicksStopBusLines(
//                clusterManager = managerStopBusLine,
//                openBottomSheetDialog = {
//                    openBottomSheetDialog.invoke()
//                }
//            )
//            Clustering(
//                items = stopBusLocations,
//                clusterManager = clusterStopBus
//            )
//        }
    }
}

@Composable
fun ApplyClicks(
    clusterManager: ClusterManager<ClusterBusLine>,
    openBottomSheetDialog: (busLine: ClusterBusLine) -> Unit
) {
    SideEffect {
        clusterManager.setOnClusterItemClickListener { clusterBusLine ->
            openBottomSheetDialog(clusterBusLine)
            false
        }
    }
}

//@Composable
//fun ApplyClicksStopBusLines(
//    clusterManager: ClusterManager<ClusterStopBusLine>,
//    openBottomSheetDialog: (busLine: ClusterStopBusLine) -> Unit
//) {
//    SideEffect {
//        clusterManager.setOnClusterItemClickListener { clusterBusLine ->
//            openBottomSheetDialog(clusterBusLine)
//            false
//        }
//    }
//}