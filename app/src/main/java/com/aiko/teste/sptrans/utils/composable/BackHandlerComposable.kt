package com.aiko.teste.sptrans.utils.composable

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.generated.destinations.MapScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun BackHandler(navigator: DestinationsNavigator) {
    BackHandler {
        navigator.popBackStack()
        navigator.navigate(MapScreenDestination)
    }
}