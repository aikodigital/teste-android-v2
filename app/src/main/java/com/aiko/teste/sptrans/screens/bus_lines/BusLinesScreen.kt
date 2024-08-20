package com.aiko.teste.sptrans.screens.bus_lines

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination<RootGraph>
@Composable
fun BusLinesScreen(navigator: DestinationsNavigator) {
    Text(text = "Bus Lines Screen")
}