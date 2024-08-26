package com.aiko.teste.sptrans.screens.bus_stop

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.aiko.teste.sptrans.data.objects.BusLine
import com.aiko.teste.sptrans.screens.bus_stop_tracking.BusStopTrackingScreenArgs
import com.aiko.teste.sptrans.utils.composable.BackHandler
import com.aiko.teste.sptrans.utils.composable.Item
import com.aiko.teste.sptrans.utils.composable.LineHeader
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.BusStopTrackingScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

private lateinit var stopCode: String
private lateinit var stopName: String
private lateinit var destinationsNavigator: DestinationsNavigator
@Destination<RootGraph>
@Composable
fun BusStopScreen(
    navigator: DestinationsNavigator,
    busStopCode: String,
    busStopName: String,
    viewModel: BusStopScreenViewModel = hiltViewModel<BusStopScreenViewModel>()
) {
    stopCode = busStopCode
    stopName = busStopName
    destinationsNavigator = navigator

    var busStopLines: List<BusLine> by remember {
        mutableStateOf(listOf())
    }

    viewModel.getBusStopPrevisions(busStopCode)
    BackHandler(navigator)
    CategorizedLazyColumn(
        busLines = busStopLines,
        onHeaderClick = { lines -> handleHeaderClick(lines) })

    viewModel.busStopLines.observe(LocalLifecycleOwner.current) { result ->
        busStopLines = result
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CategorizedLazyColumn(
    busLines: List<BusLine>,
    modifier: Modifier = Modifier,
    onHeaderClick: (List<BusLine>) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        stickyHeader {

            LineHeader(
                text = "Parada: $stopName",
                backgroundColor = Color.Black,
                contentColor = Color.White,
                onClick = { onHeaderClick(busLines) }
            )
        }
        busLines.forEach { line ->
            stickyHeader {
                LineHeader(
                    "Linha: ${line.lineStart} - ${line.lineEnd}",
                    onClick = { onHeaderClick(listOf(line)) })
            }
            items(line.positions) { bus ->
                Item(
                    "Código do ônibus: ${bus.busCode}\n" +
                            "Previsão de chegada: ${bus.previsionTime}"
                )
            }
        }
    }
}

private fun handleHeaderClick(busLines: List<BusLine>) {
    val navArgs = BusStopTrackingScreenArgs(stopCode, busLines.toTypedArray())
    destinationsNavigator.navigate(BusStopTrackingScreenDestination(navArgs))
}