package com.aiko.teste.sptrans.screens.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.aiko.teste.sptrans.data.objects.BusLine
import com.aiko.teste.sptrans.data.objects.BusStop
import com.aiko.teste.sptrans.utils.composable.BackHandler
import com.aiko.teste.sptrans.utils.composable.Item
import com.aiko.teste.sptrans.utils.composable.LineHeader
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.BusLineScreenDestination
import com.ramcosta.composedestinations.generated.destinations.BusStopScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

private lateinit var searchScreenViewModel: SearchScreenViewModel
private lateinit var destinationsNavigator: DestinationsNavigator

@Destination<RootGraph>
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator,
    viewModel: SearchScreenViewModel = hiltViewModel<SearchScreenViewModel>()
) {
    searchScreenViewModel = viewModel
    destinationsNavigator = navigator
    var busStops: List<BusStop> by remember { mutableStateOf(listOf()) }
    var busLines: List<BusLine> by remember { mutableStateOf(listOf()) }
    val busStopsObserver = Observer<List<BusStop>> { result -> busStops = result }
    val busLinesObserver = Observer<List<BusLine>> { result -> busLines = result }

    searchScreenViewModel.busStops.observe(LocalLifecycleOwner.current, busStopsObserver)
    searchScreenViewModel.busLines.observe(LocalLifecycleOwner.current, busLinesObserver)

    BackHandler(navigator)

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar()

        SearchResults(busStops, busLines)
    }
}

@Composable
fun SearchBar() {
    var query by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    OutlinedTextField(
        value = query,
        onValueChange = {
            query = it
        },
        placeholder = { Text(text = "Pesquise uma linha ou uma parada...") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .focusRequester(focusRequester),
        trailingIcon = {
            IconButton(onClick = {
                searchScreenViewModel.search(query)
            }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search,
            autoCorrectEnabled = false
        ),
        keyboardActions = KeyboardActions(onSearch = { searchScreenViewModel.search(query) })
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchResults(busStops: List<BusStop>, busLines: List<BusLine>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        if (busStops.isEmpty() && busLines.isEmpty()) {
            item {
                Text(
                    "Nenhum resultado no momento.",
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    fontSize = 16.sp
                )
            }
        } else {
            if (busStops.isNotEmpty()) {
                stickyHeader {
                    LineHeader("Paradas encontradas:", displayIcon = false)
                }
                items(busStops) { stop ->
                    Item(
                        stop.stopName,
                        displayIcon = true,
                        onClick = {
                            destinationsNavigator.navigate(
                                BusStopScreenDestination(
                                    stop.stopCode,
                                    stop.stopName
                                )
                            )
                        })
                }
            }

            if (busLines.isNotEmpty()) {
                stickyHeader {
                    LineHeader("Linhas encontradas:", displayIcon = false)
                }
                items(busLines) { line ->
                    Item(
                        "${line.mainTerminal} - ${line.secondaryTerminal}",
                        displayIcon = true,
                        onClick = { destinationsNavigator.navigate(BusLineScreenDestination(line)) })
                }
            }
        }
    }
}
