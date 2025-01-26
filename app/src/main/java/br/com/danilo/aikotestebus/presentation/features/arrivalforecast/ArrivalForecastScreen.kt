package br.com.danilo.aikotestebus.presentation.features.arrivalforecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import br.com.danilo.aikotestebus.R
import br.com.danilo.aikotestebus.domain.model.StopDetail
import br.com.danilo.aikotestebus.presentation.components.ArrivalForecastItem
import br.com.danilo.aikotestebus.presentation.components.GenericError
import br.com.danilo.aikotestebus.presentation.components.StopDetailItem
import br.com.danilo.aikotestebus.presentation.navigation.BusRoute
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_24
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_36
import br.com.danilo.aikotestebus.presentation.util.state.ArrivalForecastState
import br.com.danilo.aikotestebus.ui.theme.colorsMain
import org.koin.androidx.compose.koinViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

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

    LaunchedEffect(Unit) {
        viewModel.fetchArrivalForecast(idStop, idLine)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.bus_arrival_top_bar_title),
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
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            StopDetailItem(stopDetail) { stop ->
                val encodedNameStop = URLEncoder.encode(stop.name, StandardCharsets.UTF_8.toString())
                    .replace("+", " ")
                val encodedAddressStop = URLEncoder.encode(stop.address, StandardCharsets.UTF_8.toString())
                    .replace("+", " ")

                navController.navigate(
                    BusRoute.BusStopMap.route.replace(
                        "{nameStop}", encodedNameStop
                    ).replace(
                        "{addressStop}", encodedAddressStop
                    ).replace(
                        "{latitude}", stop.latitude.toString()
                    ).replace(
                        "{longitude}", stop.longitude.toString()
                    )
                )
            }

            HorizontalDivider()

            when (uiState) {
                is ArrivalForecastState.Success -> {
                    val data = (uiState as ArrivalForecastState.Success).item

                    if (data.busStop.busList.isEmpty()) {
                        GenericError(
                            iconResId = R.drawable.sentiment_dissatisfied_24px,
                            heading = stringResource(R.string.bus_arrival_empty_heading),
                            paragraph = stringResource(R.string.bus_arrival_empty_paragraph)
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(data.busStop.busList) {
                                ArrivalForecastItem(arrivalForecast = it) { bus ->
                                    navController.navigate(
                                        BusRoute.BusArrivalMap.route.replace(
                                            "{prefixBus}", bus.prefixNumber.toString()
                                        ).replace(
                                            "{idStop}", idStop.toString()
                                        ).replace(
                                            "{idLine}", idLine.toString()
                                        ).replace(
                                            "{latitude}", bus.latitude.toString()
                                        ).replace(
                                            "{longitude}", bus.longitude.toString()
                                        )
                                    )
                                }
                                HorizontalDivider()
                            }
                        }
                    }
                }

                is ArrivalForecastState.Error -> {
                    Column(Modifier.fillMaxSize()) {
                        GenericError(
                            iconResId = R.drawable.sentiment_dissatisfied_24px,
                            heading = stringResource(R.string.bus_arrival_error_heading),
                            paragraph = stringResource(R.string.bus_arrival_error_paragraph)
                        )
                    }
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
                                .size(spacing_36)
                        )
                    }
                }
            }
        }
    }
}