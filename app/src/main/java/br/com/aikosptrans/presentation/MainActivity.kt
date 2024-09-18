package br.com.aikosptrans.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.aikosptrans.presentation.navigation.AppNavHost
import br.com.aikosptrans.ui.theme.BaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseTheme {
                AppNavHost()
            }
        }
    }
}