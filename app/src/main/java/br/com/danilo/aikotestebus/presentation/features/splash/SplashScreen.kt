package br.com.danilo.aikotestebus.presentation.features.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import br.com.danilo.aikotestebus.presentation.components.organism.LoadingOrganism
import br.com.danilo.aikotestebus.presentation.navigation.BusRoute
import br.com.danilo.aikotestebus.presentation.util.state.SplashState
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    navController: NavController,
    splashViewModel: SplashViewModel = koinViewModel()
) {
    val uiState by splashViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        splashViewModel.fetchAuthenticator()
    }

    when (uiState) {
        is SplashState.Success -> {
                navController.navigate(
                    BusRoute.BusLineDetails.route
                )

        }

        is SplashState.Error -> {

        }

        SplashState.LoadingSplash -> {
            LoadingOrganism()
        }
    }
}