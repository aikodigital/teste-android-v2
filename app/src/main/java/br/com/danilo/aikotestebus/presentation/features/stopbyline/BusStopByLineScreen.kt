package br.com.danilo.aikotestebus.presentation.features.stopbyline

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.danilo.aikotestebus.R
import br.com.danilo.aikotestebus.domain.model.LineDetail
import br.com.danilo.aikotestebus.domain.model.StopDetail
import br.com.danilo.aikotestebus.presentation.components.SearchField
import br.com.danilo.aikotestebus.presentation.features.lines.LineDetailItem
import br.com.danilo.aikotestebus.presentation.navigation.BusRoute
import br.com.danilo.aikotestebus.presentation.util.encodeLineDetailItem
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
                        text = "Paradas",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp),
                        textAlign = TextAlign.Start,
                    )
                },
                modifier = Modifier.height(70.dp),
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            HorizontalDivider()
            LineDetailItem(lineDetail = lineDetail) {}

            SearchField(
                label = "Digite a parada aqui",
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
                                        "{idStop}", lineDetail.lineId.toString()
                                    )
                                )
                            }
                            HorizontalDivider()
                        }
                    }
                }

                is BusStopByLineState.Error -> {
                    Text(text = "Erro ao carregar os dados.")
                }

                BusStopByLineState.Loading -> {
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

@Composable
fun StopDetailItem(
    stopDetail: StopDetail,
    clickedItem: (StopDetail) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
            .clickable { clickedItem.invoke(stopDetail) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_point_24),
            contentDescription = "Ícone de ônibus",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(48.dp)
                .padding(end = 24.dp)
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Nome da parada: ${stopDetail.name}",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Endereço: ${stopDetail.address}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}