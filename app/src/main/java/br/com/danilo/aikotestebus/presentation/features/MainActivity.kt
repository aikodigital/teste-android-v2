package br.com.danilo.aikotestebus.presentation.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import br.com.danilo.aikotestebus.presentation.navigation.BusRoute
import br.com.danilo.aikotestebus.presentation.navigation.NavigationGraph
import br.com.danilo.aikotestebus.ui.theme.AikoTesteBusTheme
import com.google.android.gms.maps.model.LatLng

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
                            if (navController.currentBackStackEntry?.destination?.route == "${BusRoute.BusTabContainer.route}/{latitude}/{longitude}") {
                                finish()
                            }
                            NavigationGraph(
                                initialRoute = BusRoute.BusAuthenticator.route,
                                navController = navController
                            )
                        }
                    }
                )
            }
        }
    }
}