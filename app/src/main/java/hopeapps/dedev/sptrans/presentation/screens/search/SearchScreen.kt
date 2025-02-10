package hopeapps.dedev.sptrans.presentation.screens.search

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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import hopeapps.dedev.sptrans.R
import hopeapps.dedev.sptrans.domain.models.BusLine
import hopeapps.dedev.sptrans.domain.models.BusStop
import hopeapps.dedev.sptrans.presentation.design_system.components.BusListItem
import hopeapps.dedev.sptrans.presentation.design_system.components.BusStopItem
import hopeapps.dedev.sptrans.presentation.design_system.components.EmptyState
import hopeapps.dedev.sptrans.presentation.design_system.components.MySegmentedButton
import hopeapps.dedev.sptrans.presentation.design_system.components.SearchBar
import hopeapps.dedev.sptrans.presentation.design_system.theme.Dimens
import hopeapps.dedev.sptrans.presentation.design_system.theme.Dimens.Dimens_4_Dp
import hopeapps.dedev.sptrans.presentation.design_system.theme.Dimens.Dimens_8_Dp
import hopeapps.dedev.sptrans.presentation.design_system.utils.keyboardAsState
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreenRoot(
    viewModel: SearchViewModel = koinViewModel(),
    onItemBusLineClick: (busLine: BusLine) -> Unit,
    onItemBusStopClick: (busStop: BusStop) -> Unit,
    navigateToMaps: () -> Unit
) {
    SearchScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                is SearchScreenAction.ClickListBusLine -> onItemBusLineClick(action.busLine)
                is SearchScreenAction.ClickListBusStop -> onItemBusStopClick(action.busStop)
                is SearchScreenAction.NavigateToMaps -> navigateToMaps()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun SearchScreen(
    state: SearchScreenState,
    onAction: (SearchScreenAction) -> Unit,
) {
    var searchText by remember { mutableStateOf("") }
    val lazyListState = rememberLazyListState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val isKeyboardOpen by keyboardAsState()

    val firstItemIsVisible = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }
    LaunchedEffect(firstItemIsVisible) {
        if (isKeyboardOpen) {
            keyboardController?.hide()
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAction(SearchScreenAction.NavigateToMaps) },
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
                    .padding(
                        horizontal = Dimens.Dimens_20_Dp,
                        vertical = Dimens.SegmentedButtonVerticalPadding
                    ),
                selectedOption = state.searchBusStop,
                firstLabel = stringResource(R.string.bus_stop),
                secondLabel = stringResource(R.string.bus),
                onFirstClick = { onAction(SearchScreenAction.ClickToSwitchTab(searchBusStop = true)) },
                onSecondClick = { onAction(SearchScreenAction.ClickToSwitchTab(searchBusStop = false)) }
            )

            SearchBar(
                modifier = Modifier
                    .padding(horizontal = Dimens.Dimens_16_Dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(Dimens_8_Dp)
                    ),
                hintText = if (state.searchBusStop)
                    stringResource(R.string.hint_search_stop_bus)
                else
                    stringResource(R.string.hint_search_bus_lines),
                value = searchText,
                onValueChanged = { newText ->
                    searchText = newText
                    onAction(SearchScreenAction.SearchItems(newText))
                }
            )

            Spacer(modifier = Modifier.height(Dimens.Dimens_16_Dp))

            Box(
                modifier = Modifier
                    .padding(horizontal = Dimens.Dimens_16_Dp)
                    .weight(1f)
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(Dimens_8_Dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(Dimens.ButtonHeight),
                        color = MaterialTheme.colorScheme.primary,
                        strokeWidth = Dimens_4_Dp
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.background),
                        verticalArrangement = Arrangement.spacedBy(Dimens.Dimens_8_Dp),
                        contentPadding = PaddingValues(vertical = Dimens.Dimens_16_Dp),
                        state = lazyListState
                    ) {
                        if (!state.searchBusStop) {
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
        onAction = {}
    )
}
