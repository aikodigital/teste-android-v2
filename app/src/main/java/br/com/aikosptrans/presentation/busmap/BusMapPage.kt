package br.com.aikosptrans.presentation.busmap

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import br.com.aikosptrans.R
import br.com.aikosptrans.presentation.atomic.template.MapTemplate
import br.com.aikosptrans.presentation.busmap.viewmodel.BusMapViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BusMapPage(
    navController: NavController,
    viewModel: BusMapViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getBusLocation()
    }

    MapTemplate(
        navController = navController,
        items = uiState.buses,
        iconMarker = painterResource(R.drawable.ic_bus)
    )
}