package br.com.danilo.aikotestebus.presentation.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import br.com.danilo.aikotestebus.presentation.navigation.BusRoute
import br.com.danilo.aikotestebus.presentation.navigation.NavigationGraph
import br.com.danilo.aikotestebus.ui.theme.AikoTesteBusTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AikoTesteBusTheme {
                NavigationGraph(
                    initialRoute = BusRoute.BusAuthenticator.route,
                    navController = navController
                )
            }
        }
    }
}