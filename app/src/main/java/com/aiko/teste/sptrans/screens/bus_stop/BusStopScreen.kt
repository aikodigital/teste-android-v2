package com.aiko.teste.sptrans.screens.bus_stop

import androidx.activity.compose.BackHandler
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.generated.destinations.MapScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination<RootGraph>
@Composable
fun BusStopScreen(navigator: DestinationsNavigator, busStopCode: String) {
    Text(text = "Bus Stop Screen $busStopCode")
    BackHandler {
        navigator.popBackStack()
        navigator.navigate(MapScreenDestination)
    }
}