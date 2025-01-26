package br.com.danilo.aikotestebus.presentation.features.arrivalforecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.danilo.aikotestebus.R
import br.com.danilo.aikotestebus.domain.model.StopDetail
import br.com.danilo.aikotestebus.presentation.components.ArrivalForecastItem
import br.com.danilo.aikotestebus.presentation.components.SearchField
import br.com.danilo.aikotestebus.presentation.components.StopDetailItem
import br.com.danilo.aikotestebus.presentation.navigation.BusRoute
import br.com.danilo.aikotestebus.presentation.util.encodeLineDetailItem
import br.com.danilo.aikotestebus.presentation.util.state.ArrivalForecastState
import br.com.danilo.aikotestebus.ui.theme.colorsMain
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArrivalForecastScreen(
    idStop: Int,
    idLine: Int,
    stopDetail: StopDetail,
    navController: NavController,
    viewModel: ArrivalForecastViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.fetchArrivalForecast(idStop, idLine)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Previsões",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp),
                        textAlign = TextAlign.Start,
                    )
                },
                modifier = Modifier.height(70.dp),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        },
                        modifier = Modifier.size(24.dp)
                    ) {
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            StopDetailItem(stopDetail) {}

            SearchField(
                label = "Procure o ônibus da linha aqui",
                value = searchQuery,
                onValueChanged = {
                    viewModel.onSearchQueryChange(it)
                }) {
                viewModel.onSearchQueryChange(it)
            }
            HorizontalDivider()

            when (uiState) {
                is ArrivalForecastState.Success -> {
                    val data = (uiState as ArrivalForecastState.Success).item

                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(data.busStop.busList) {
                            ArrivalForecastItem(arrivalForecast = it) { bus ->
                                navController.navigate(
                                    BusRoute.BusTabContainer.route.replace(
                                        "{latitude}", bus.latitude.toString()
                                    ).replace(
                                        "{longitude}", bus.longitude.toString()
                                    )
                                ) {
                                    popUpTo(BusRoute.BusArrivalForecastTime.route) {
                                        inclusive = true
                                    }
                                }
                            }
                            HorizontalDivider()
                        }
                    }
                }

                is ArrivalForecastState.Error -> {
                    Text(text = "Erro ao carregar os dados.")
                }

                ArrivalForecastState.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            color = Color.Black,
                            modifier = Modifier
                                .size(36.dp)
                        )
                    }
                }
            }
        }
    }
}