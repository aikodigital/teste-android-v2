package hopeapps.dedev.sptrans.presentation.bus_stop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hopeapps.dedev.sptrans.ui.components.BusListItem
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


@Composable
fun BusStopScreen(
    state: BusStopState,
    onAction: (BusStopAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        BusListItem(
            modifier = Modifier
                .padding(
                    horizontal = 20.dp
                )
                .padding(top = 32.dp, bottom = 16.dp),
            firstLabel = "",
            secondLabel = 0,
            mainTerminal = "",
            secondaryTerminal = "",
            onClickListener = {
                onAction(BusStopAction.ViewInMapClick)
            }
        )

        ViewOnMapCard(
            onClick = {

            }
        )

        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = "Previsões da parada:"
        )

        LazyColumn {
            items(state.busStopPrediction) {
                PredictionListItem()
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