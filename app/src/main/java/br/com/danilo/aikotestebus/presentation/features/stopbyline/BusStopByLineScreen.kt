package br.com.danilo.aikotestebus.presentation.features.stopbyline

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
import br.com.danilo.aikotestebus.domain.model.LineDetail
import br.com.danilo.aikotestebus.presentation.components.GenericError
import br.com.danilo.aikotestebus.presentation.components.LineDetailItem
import br.com.danilo.aikotestebus.presentation.components.SearchField
import br.com.danilo.aikotestebus.presentation.components.StopDetailItem
import br.com.danilo.aikotestebus.presentation.navigation.BusRoute
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_24
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_36
import br.com.danilo.aikotestebus.presentation.util.encodeStopDetailItem
import br.com.danilo.aikotestebus.presentation.util.state.BusStopByLineState
import br.com.danilo.aikotestebus.ui.theme.colorsMain
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusStopByLineScreen(
    lineDetail: LineDetail,
    navController: NavController,
    viewModel: BusStopByLineViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchBusStopByLine(lineDetail.lineId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.bus_stop_by_line_app_bar),
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
            HorizontalDivider()
            LineDetailItem(lineDetail = lineDetail) {}

            SearchField(
                label = stringResource(R.string.bus_stop_by_line_search_label),
                value = searchQuery,
                onValueChanged = {
                    viewModel.onSearchQueryChange(it)
                }) {
                viewModel.onSearchQueryChange(it)
            }

            when (uiState) {
                is BusStopByLineState.Success -> {
                    val data = (uiState as BusStopByLineState.Success).items
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(data) {
                            StopDetailItem(stopDetail = it) { stopDetail ->
                                navController.navigate(
                                    BusRoute.BusArrivalForecastTime.route.replace(
                                        "{idStop}", stopDetail.stopId.toString()
                                    ).replace(
                                        "{idLine}", lineDetail.lineId.toString()
                                    ).replace(
                                        "{item}", encodeStopDetailItem(stopDetail)
                                    )
                                )
                            }
                            HorizontalDivider()
                        }
                    }
                }

                is BusStopByLineState.Error -> {
                    Column(Modifier.fillMaxSize()) {
                        GenericError(
                            iconResId = R.drawable.sentiment_dissatisfied_24px,
                            heading = stringResource(R.string.bus_stop_by_line_error_heading),
                            paragraph = stringResource(R.string.bus_stop_by_line_error_paragraph)
                        )
                    }                }

                BusStopByLineState.Loading -> {
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