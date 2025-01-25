package br.com.danilo.aikotestebus.presentation.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
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
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { paddingValues ->
                        Column(
                            modifier = Modifier.fillMaxSize()
                                .padding(paddingValues)

                        ) {
                            if (navController.currentBackStackEntry?.destination?.route == BusRoute.BusTabContainer.route) {
                                finish()
                            }
                            NavigationGraph(
                                initialRoute = BusRoute.BusSplash.route,
                                navController = navController
                            )
                        }
                    }

                )
            }
        }
    }
}