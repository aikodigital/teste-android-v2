package hopeapps.dedev.sptrans

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import hopeapps.dedev.sptrans.presentation.design_system.theme.SpTransTheme
import hopeapps.dedev.sptrans.presentation.navigation.NavigationRoot
import hopeapps.dedev.sptrans.utils.ConnectivityObserver
import hopeapps.dedev.sptrans.utils.NetworkConnectivityObserver
import hopeapps.dedev.sptrans.utils.NoInternetScreen

class MainActivity : ComponentActivity() {

    private lateinit var connectivityObserver: ConnectivityObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        setContent {
            SpTransTheme {
                val status by connectivityObserver.observe().collectAsState(
                    initial = ConnectivityObserver.Status.Available
                )
                val navController = rememberNavController()
                if (status == ConnectivityObserver.Status.Unavailable) {
                    NoInternetScreen()
                } else {
                    NavigationRoot(
                        navController = navController
                    )
                }
            }
        }
    }
}
