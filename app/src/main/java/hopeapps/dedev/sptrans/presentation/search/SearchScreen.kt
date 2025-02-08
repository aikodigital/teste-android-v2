package hopeapps.dedev.sptrans.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hopeapps.dedev.sptrans.R
import hopeapps.dedev.sptrans.domain.models.BusLine
import hopeapps.dedev.sptrans.domain.models.BusStop
import hopeapps.dedev.sptrans.presentation.search.SearchScreenAction.ClickToSwitchTab
import hopeapps.dedev.sptrans.ui.components.BusListItem
import hopeapps.dedev.sptrans.ui.components.BusStopItem
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


@Composable
fun SearchScreen(
    state: SearchScreenState,
    onAction: (SearchScreenAction) -> Unit
) {

    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        MySegmentedButton(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 24.dp),
            selectedOption = state.searchBusLines,
            firstLabel = stringResource(R.string.bus),
            secondLabel = stringResource(R.string.bus_stop),
            onFirstClick = { onAction(ClickToSwitchTab(searchBusLines = true)) },
            onSecondClick = { onAction(ClickToSwitchTab(searchBusLines = false)) }
        )

        SearchBar(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .background(MaterialTheme.colorScheme.surface),
            hintText = stringResource(R.string.hint_search_bus_lines),
            value = searchText,
            onValueChanged = {
                newText -> searchText = newText
                onAction(SearchScreenAction.SearchItems(newText))
            }
        )

        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 16.dp)
                .weight(1f)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primary,
                    trackColor = MaterialTheme.colorScheme.secondaryContainer,
                    strokeWidth = 4.dp
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (state.searchBusLines) {
                        items(state.busLinesItems) { busLine ->
                            BusListItem(
                                firstLabel = busLine.firstLabelNumber,
                                secondLabel = busLine.secondLabelNumber,
                                mainTerminal = busLine.mainTerminal,
                                secondaryTerminal = busLine.secondaryTerminal,
                                onClickListener = {
                                    onAction(SearchScreenAction.ClickListBusLine(
                                        busLine = busLine
                                    ))
                                }
                            )
                        }
                    } else {
                        items(state.busStopItems) { busStop ->
                            BusStopItem(
                                onClickListener = {
                                    onAction(SearchScreenAction.ClickListBusStop(busStop))
                                }
                            )
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