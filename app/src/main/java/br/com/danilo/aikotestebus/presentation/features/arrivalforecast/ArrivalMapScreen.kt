package br.com.danilo.aikotestebus.presentation.features.arrivalforecast

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import br.com.danilo.aikotestebus.R
import br.com.danilo.aikotestebus.presentation.components.ClusteringMap
import br.com.danilo.aikotestebus.presentation.util.MAX_ZOOM
import br.com.danilo.aikotestebus.presentation.util.MIN_ZOOM
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_24
import br.com.danilo.aikotestebus.presentation.util.ZERO
import br.com.danilo.aikotestebus.presentation.util.createMapMarkerFromPrefix
import br.com.danilo.aikotestebus.presentation.util.state.ArrivalForecastState
import br.com.danilo.aikotestebus.ui.theme.colorsMain
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArrivalMapScreen(
    prefixBus: Int,
    idStop: Int,
    idLine: Int,
    initialLocation: LatLng,
    navController: NavController,
    viewModel: ArrivalForecastViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    DisposableEffect(prefixBus) {
        if (prefixBus != ZERO) {
            viewModel.startPeriodicTask(idStop, idLine)
        } else {
            viewModel.stopPeriodicTask()
        }

        onDispose {
            viewModel.stopPeriodicTask()
        }
    }

    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                compassEnabled = false,
                zoomControlsEnabled = false,
                mapToolbarEnabled = true
            )
        )
    }

    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initialLocation, 14f)
    }

    val mapProperties by remember {
        mutableStateOf(
            MapProperties(maxZoomPreference = MAX_ZOOM, minZoomPreference = MIN_ZOOM)
        )
    }

    var moveLocate by remember {
        mutableStateOf(initialLocation)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.bus_top_appbar_arrival_map, prefixBus),
                        modifier = Modifier
                            .padding(start = spacing_24),
                        textAlign = TextAlign.Start,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    },
                        modifier = Modifier.size(spacing_24)) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "Voltar"
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = colorsMain.inactiveContent,
                    titleContentColor = colorsMain.text,
                    navigationIconContentColor = colorsMain.text
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(moveLocate, 17f)

                },
                containerColor = colorsMain.buttonBackground
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.point_scan_24px),
                    contentDescription = "Procurar"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                GoogleMap(
                    cameraPositionState = cameraPositionState,
                    properties = mapProperties,
                    uiSettings = mapUiSettings
                ) {
                    when (uiState) {
                        is ArrivalForecastState.Success -> {
                            val data = (uiState as ArrivalForecastState.Success).item

                            ClusteringMap(
                                clusterItems = createMapMarkerFromPrefix(data, prefixBus) { locate ->
                                    moveLocate = locate
                                },
                                markerIcon = painterResource(R.drawable.ic_bus),
                            )
                        }
                        else -> {}
                    }
                }
            }
        }
}