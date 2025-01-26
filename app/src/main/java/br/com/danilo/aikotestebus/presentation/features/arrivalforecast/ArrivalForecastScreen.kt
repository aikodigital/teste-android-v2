package br.com.danilo.aikotestebus.presentation.features.arrivalforecast

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArrivalForecastScreen(
    idStop: Int,
    idLine: Int,
    navController: NavController,
    viewModel: ArrivalForecastViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchArrivalForecast(idStop, idLine)
    }
}