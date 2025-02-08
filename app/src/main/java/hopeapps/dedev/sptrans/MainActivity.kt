package hopeapps.dedev.sptrans

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import hopeapps.dedev.sptrans.presentation.search.SearchScreenRoot
import hopeapps.dedev.sptrans.ui.navigation.NavigationRoot
import hopeapps.dedev.sptrans.ui.theme.SpTransTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpTransTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier.padding(innerPadding)
                    ) {

                        val navController = rememberNavController()

                        NavigationRoot(
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}
