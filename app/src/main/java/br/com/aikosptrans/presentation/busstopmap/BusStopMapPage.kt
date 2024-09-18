package br.com.aikosptrans.presentation.busstopmap

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import br.com.aikosptrans.R
import br.com.aikosptrans.presentation.atomic.molecule.SearchFieldMolecule
import br.com.aikosptrans.presentation.atomic.template.MapTemplate
import br.com.aikosptrans.presentation.busstopmap.viewmodel.BusStopMapViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BusStopMapPage(
    navController: NavController,
    viewModel: BusStopMapViewModel = koinViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getBusStop()
    }

    MapTemplate(
        navController = navController,
        items = uiState.busStops,
        iconMarker = painterResource(R.drawable.ic_stop_bus),
        topBar = {
            SearchFieldMolecule(
                label = stringResource(R.string.bus_stop_hint),
                value = uiState.query,
                onValueChanged = viewModel::onQueryChanged,
                onSearchPressed = viewModel::getBusStop
            )
        }
    )
}