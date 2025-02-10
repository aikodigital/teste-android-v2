package hopeapps.dedev.sptrans.presentation.bus_stop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import hopeapps.dedev.sptrans.R
import hopeapps.dedev.sptrans.domain.models.DynamicPoint
import hopeapps.dedev.sptrans.domain.models.StaticPoint
import hopeapps.dedev.sptrans.ui.components.BusStopItem
import hopeapps.dedev.sptrans.ui.components.EmptyState
import hopeapps.dedev.sptrans.ui.components.PredictionListItem
import hopeapps.dedev.sptrans.ui.components.ViewOnMapCard
import hopeapps.dedev.sptrans.ui.theme.Dimens

@Composable
fun BusStopDetailsRoot(
    viewModel: BusStopViewModel,
    viewInMapClick: (mapPoint: StaticPoint) -> Unit,
    viewBusInMap: (mapPoint: DynamicPoint) -> Unit,
    navigateToBack: () -> Unit
) {
    BusStopScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                is BusStopAction.ViewInMapClick -> viewInMapClick(action.staticPoint)
                is BusStopAction.ViewBusInMap -> viewBusInMap(action.dynamicPoint)
                is BusStopAction.NavigateBack -> navigateToBack()
            }
            viewModel.onAction(action)
        }
    )
}


@Composable
fun BusStopScreen(
    state: BusStopState,
    onAction: (BusStopAction) -> Unit
) {
    Scaffold(
        topBar = { BusStopTopBar(state.busStop?.name, onAction) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            BusStopInfoSection(state, onAction)
            BusStopPredictionsList(state, onAction)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusStopTopBar(busStopName: String?, onAction: (BusStopAction) -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = busStopName ?: stringResource(R.string.bus_stop),
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
}

@Composable
fun BusStopInfoSection(state: BusStopState, onAction: (BusStopAction) -> Unit) {
    Column(
        modifier = Modifier.padding(horizontal = Dimens.Dimens_16_Dp, vertical = Dimens.Dimens_16_Dp)
    ) {
        BusStopItem(
            name = state.busStop?.name ?: "",
            address = state.busStop?.address ?: ""
        )

        Spacer(modifier = Modifier.height(Dimens.Dimens_16_Dp))

        ViewOnMapCard(
            onClick = { onAction(BusStopAction.ViewInMapClick(state.busStopPoint)) }
        )

        Spacer(modifier = Modifier.height(Dimens.Dimens_16_Dp))

        Text(
            text = stringResource(R.string.bus_stop_predictions),
            style = MaterialTheme.typography.titleMedium
        )
    }
}


@Composable
fun BusStopPredictionsList(state: BusStopState, onAction: (BusStopAction) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(Dimens.Dimens_8_Dp),
        contentPadding = PaddingValues(horizontal = Dimens.Dimens_16_Dp, vertical = Dimens.Dimens_16_Dp)
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
                    predictionTime = prediction.predictionTime,
                    origin = prediction.origin,
                    destination = prediction.destination,
                    accessibleVehicle = prediction.accessibleVehicle,
                    lastUpdateTime = prediction.lastUpdateTime,
                    onClickListener = {
                        onAction(
                            BusStopAction.ViewBusInMap(
                                DynamicPoint(
                                    latitude = prediction.py,
                                    longitude = prediction.px,
                                    name = "${prediction.origin} -> ${prediction.destination}",
                                    accessible = prediction.accessibleVehicle,
                                    id = prediction.idLine,
                                    lastUpdate = "",
                                    prefix = 0
                                )
                            )
                        )
                    }
                )
            }
        }
    }
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