package com.aiko.teste.sptrans.screens.bus_stop

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.aiko.teste.sptrans.data.objects.Line
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.MapScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

private lateinit var _busStopCode: String
private lateinit var _busStopName: String
@Destination<RootGraph>
@Composable
fun BusStopScreen(
    navigator: DestinationsNavigator,
    busStopCode: String,
    busStopName: String,
    viewModel: BusStopScreenViewModel = hiltViewModel<BusStopScreenViewModel>()
) {
    _busStopCode = busStopCode
    _busStopName = busStopName
    var busStopLines: List<Line> by remember {
        mutableStateOf(listOf())
    }

    viewModel.getBusStopPrevisions(busStopCode)
    HandleBackButton(navigator)
    CategorizedLazyColumn(
        lines = busStopLines,
        onHeaderClick = { lines -> handleHeaderClick(lines) })

    viewModel.busStopLines.observe(LocalLifecycleOwner.current) { result ->
        busStopLines = result
    }
}

@Composable
fun HandleBackButton(navigator: DestinationsNavigator) {
    BackHandler {
        navigator.popBackStack()
        navigator.navigate(MapScreenDestination)
    }
}

@Composable
private fun LineHeader(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = Color.Unspecified,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(16.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = contentColor,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = "Open map",
            modifier = Modifier.size(24.dp),
            tint = contentColor
        )
    }
}

@Composable
private fun BusItem(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 14.sp,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun CategorizedLazyColumn(
    lines: List<Line>,
    modifier: Modifier = Modifier,
    onHeaderClick: (List<Line>) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        stickyHeader {
            LineHeader(
                text = "Parada: $_busStopName",
                backgroundColor = Color.Black,
                contentColor = Color.White,
                onClick = { onHeaderClick(lines) }
            )
        }
        lines.forEach { line ->
            stickyHeader {
                LineHeader(
                    "Linha: ${line.lineStart} - ${line.lineEnd}",
                    onClick = { onHeaderClick(listOf(line)) })
            }
            items(line.positions) { bus ->
                BusItem(
                    "Código do ônibus: ${bus.busCode}\n" +
                            "Previsão de chegada: ${bus.previsionTime}"
                )
            }
        }
    }
}

private fun handleHeaderClick(lines: List<Line>) {

}