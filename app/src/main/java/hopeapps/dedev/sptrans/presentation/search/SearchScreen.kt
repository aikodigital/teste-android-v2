package hopeapps.dedev.sptrans.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hopeapps.dedev.sptrans.R
import hopeapps.dedev.sptrans.domain.models.BusLine
import hopeapps.dedev.sptrans.domain.models.BusStop
import hopeapps.dedev.sptrans.ui.components.BusListItem
import hopeapps.dedev.sptrans.ui.components.BusStopItem
import hopeapps.dedev.sptrans.ui.components.EmptyState
import hopeapps.dedev.sptrans.ui.components.MySegmentedButton
import hopeapps.dedev.sptrans.ui.components.SearchBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreenRoot(
    viewModel: SearchViewModel = koinViewModel(),
    onItemBusLineClick: (busLine: BusLine) -> Unit,
    onItemBusStopClick: (busStop: BusStop) -> Unit
) {
    SearchScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                is SearchScreenAction.ClickListBusLine -> onItemBusLineClick(action.busLine)
                is SearchScreenAction.ClickListBusStop -> onItemBusStopClick(action.busStop)
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    state: SearchScreenState,
    onAction: (SearchScreenAction) -> Unit,
) {
    var searchText by remember { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                },
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                content = {
                    Icon(
                        painter = painterResource(R.drawable.map_24),
                        contentDescription = ""
                    )
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            MySegmentedButton(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 32.dp),
                selectedOption = state.searchBusLines,
                firstLabel = stringResource(R.string.bus),
                secondLabel = stringResource(R.string.bus_stop),
                onFirstClick = { onAction(SearchScreenAction.ClickToSwitchTab(searchBusLines = true)) },
                onSecondClick = { onAction(SearchScreenAction.ClickToSwitchTab(searchBusLines = false)) }
            )

            SearchBar(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(8.dp)
                    ),
                hintText = if (state.searchBusLines)
                    stringResource(R.string.hint_search_bus_lines)
                else
                    stringResource(R.string.hint_search_stop_bus),
                value = searchText,
                onValueChanged = { newText ->
                    searchText = newText
                    onAction(SearchScreenAction.SearchItems(newText))
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .weight(1f)
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(48.dp),
                        color = MaterialTheme.colorScheme.primary,
                        strokeWidth = 4.dp
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        contentPadding = PaddingValues(vertical = 16.dp)
                    ) {
                        if (state.searchBusLines) {
                            if (state.busLinesItems.isEmpty()) {
                                item {
                                    EmptyState(
                                        title = stringResource(R.string.empty_state_bus_lines_title),
                                        description = stringResource(R.string.empty_state_bus_lines_description)
                                    )
                                }
                            } else {
                                items(state.busLinesItems) { busLine ->
                                    BusListItem(
                                        firstLabel = busLine.firstLabelNumber,
                                        secondLabel = busLine.secondLabelNumber,
                                        mainTerminal = busLine.mainTerminal,
                                        secondaryTerminal = busLine.secondaryTerminal,
                                        onClickListener = {
                                            onAction(
                                                SearchScreenAction.ClickListBusLine(busLine = busLine)
                                            )
                                        }
                                    )
                                }
                            }
                        } else {
                            if (state.busStopItems.isEmpty()) {
                                item {
                                    EmptyState(
                                        title = stringResource(R.string.empty_state_bus_stop_title),
                                        description = stringResource(R.string.empty_state_bus_stop_description)
                                    )
                                }
                            } else {
                                items(state.busStopItems) { busStop ->
                                    BusStopItem(
                                        onClickListener = {
                                            onAction(SearchScreenAction.ClickListBusStop(busStop))
                                        },
                                        name = busStop.name,
                                        address = busStop.address
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun SearchScreenPreview() {
    SearchScreen(
        state = SearchScreenState(),
        onAction = {

        }
    )
}