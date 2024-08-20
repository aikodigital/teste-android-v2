package com.aiko.teste.sptrans.screens.bus_stop

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination<RootGraph>
@Composable
fun BusStopScreen(navigator: DestinationsNavigator) {
    Text(text = "Bus Stop Screen")
}