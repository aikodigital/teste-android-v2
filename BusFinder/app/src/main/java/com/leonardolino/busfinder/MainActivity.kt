package com.leonardolino.busfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.leonardolino.busfinder.presentation.components.GoogleMapScreen
import com.leonardolino.busfinder.ui.theme.BusFinderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BusFinderTheme {
                GoogleMapScreen()
            }
        }
    }
}