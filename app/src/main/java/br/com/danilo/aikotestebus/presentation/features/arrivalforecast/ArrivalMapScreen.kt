package br.com.danilo.aikotestebus.presentation.features.arrivalforecast

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.danilo.aikotestebus.R
import br.com.danilo.aikotestebus.domain.model.ArrivalForecast
import br.com.danilo.aikotestebus.domain.model.entity.MapMarker
import br.com.danilo.aikotestebus.presentation.components.ClusteringMap
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
        if (prefixBus != 0) {
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
        position = CameraPosition.fromLatLngZoom(initialLocation, 15f)
    }

    val mapProperties by remember {
        mutableStateOf(
            MapProperties(maxZoomPreference = 20f, minZoomPreference = 10f)
        )
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Localização: $prefixBus",
                        modifier = Modifier
                            .padding(start = 24.dp),
                        textAlign = TextAlign.Start,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    },
                        modifier = Modifier.size(24.dp)) {
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
        }
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
                                clusterItems = createMapMarkerFromPrefix(data, prefixBus),
                                markerIcon = painterResource(R.drawable.ic_bus),
                            )
                        }

                        else -> {}
                    }
                }
            }
        }
}


fun createMapMarkerFromPrefix(
    arrivalForecast: ArrivalForecast,
    prefix: Int,
): List<MapMarker> {
    arrivalForecast.busStop.busList.forEach { relation ->
        val bus = relation.buses.firstOrNull { it.prefixNumber == prefix }
        bus?.let {
            return listOf(
                MapMarker(
                    titleText = "Ônibus:${it.prefixNumber}, Linha:${relation.letterComplete}",
                    snippetText = "Tempo estimado: ${it.arrivalForecastTime}",
                    location = LatLng(it.latitude, it.longitude)
                )
            )
        }
    }
    return emptyList()
}