package hopeapps.dedev.sptrans.presentation.line_bus

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
import hopeapps.dedev.sptrans.domain.models.DynamicPoint
import hopeapps.dedev.sptrans.ui.components.BusListItem
import hopeapps.dedev.sptrans.ui.components.BusStopItem
import hopeapps.dedev.sptrans.ui.components.EmptyState
import hopeapps.dedev.sptrans.ui.theme.Dimens

@Composable
fun LineBusRoot(
    viewModel: LineBusViewModel,
    viewInMapClick: (busLinePositions: List<DynamicPoint>) -> Unit,
    navigateBackClick: () -> Unit
) {
    LineBusScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                is LineBusAction.ViewInMapClick -> viewInMapClick(action.dynamicPoint)
                is LineBusAction.NavigateBack -> navigateBackClick()
            }
            viewModel.onAction(action)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LineBusScreen(
    state: LineBusState,
    onAction: (LineBusAction) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = state.busLine?.secondaryTerminal ?: stringResource(R.string.bus),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onAction(LineBusAction.NavigateBack) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(it)
        ) {
            BusListItem(
                modifier = Modifier.padding(
                    horizontal = Dimens.Dimens_16_Dp,
                    vertical = Dimens.Dimens_16_Dp
                ),
                firstLabel = state.busLine?.firstLabelNumber ?: "000",
                secondLabel = state.busLine?.secondLabelNumber ?: 0,
                mainTerminal = state.busLine?.mainTerminal ?: "Terminal A",
                secondaryTerminal = state.busLine?.secondaryTerminal ?: "Terminal B",
                elevation = 0.dp
            )

            Text(
                modifier = Modifier.padding(horizontal = Dimens.Dimens_16_Dp, vertical = Dimens.Dimens_12_Dp),
                text = stringResource(R.string.stop_bus_line),
                style = MaterialTheme.typography.titleMedium
            )

            LazyColumn(
                modifier = Modifier.weight(1f).padding(horizontal = Dimens.Dimens_16_Dp),
                verticalArrangement = Arrangement.spacedBy(Dimens.Dimens_16_Dp),
                contentPadding = PaddingValues(vertical = Dimens.Dimens_16_Dp)
            ) {
                if (state.busStopItems.isEmpty()) {
                    item {
                        EmptyState(
                            title = stringResource(R.string.no_bus_stops),
                            description = stringResource(R.string.no_bus_stops_description)
                        )
                    }
                } else {
                    items(state.busStopItems) { busStop ->
                        BusStopItem(
                            name = busStop.name,
                            address = busStop.address
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun LineBusPreview() {
    LineBusScreen(
        state = LineBusState(),
        onAction = {

        }
    )
}
