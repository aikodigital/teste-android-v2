package hopeapps.dedev.sptrans.presentation.bus_stop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hopeapps.dedev.sptrans.R
import hopeapps.dedev.sptrans.ui.components.BusStopItem
import hopeapps.dedev.sptrans.ui.components.EmptyState
import hopeapps.dedev.sptrans.ui.components.PredictionListItem
import hopeapps.dedev.sptrans.ui.components.ViewOnMapCard

@Composable
fun BusStopDetailsRoot(
    viewModel: BusStopViewModel,
    viewInMapClick: () -> Unit
) {
    BusStopScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                is BusStopAction.ViewInMapClick -> viewInMapClick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusStopScreen(
    state: BusStopState,
    onAction: (BusStopAction) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = state.busStop?.name ?: stringResource(R.string.bus_stop),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onAction(BusStopAction.NavigateBack) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(paddingValues)
            ) {
                BusStopItem(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    name = state.busStop?.name ?: "Nome Exemplo",
                    address = state.busStop?.address ?: "Endereço Exemplo",
                    onClickListener = { onAction(BusStopAction.ViewInMapClick) }
                )

                ViewOnMapCard(
                    onClick = { onAction(BusStopAction.ViewInMapClick) },
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
                )

                Text(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp),
                    text = stringResource(R.string.bus_stop_predictions),
                    style = MaterialTheme.typography.titleMedium
                )

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp)
                ) {
                    if (state.busStopPrediction.isEmpty()) {
                        item {
                            EmptyState(
                                title = stringResource(R.string.no_predictions_title),
                                description = stringResource(R.string.no_predictions_description)
                            )
                        }
                    } else {
                        items(state.busStopPrediction) { prediction ->
                            PredictionListItem(
                                prediction.predictionTime,
                                prediction.origin,
                                prediction.destination,
                                prediction.accessibleVehicle,
                                prediction.lastUpdateTime,
                            )
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun LineBusPreview() {
    BusStopScreen(
        state = BusStopState(),
        onAction = {

        }
    )
}