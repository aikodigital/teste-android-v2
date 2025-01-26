package br.com.danilo.aikotestebus.presentation.features.authenticator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import br.com.danilo.aikotestebus.presentation.components.Loading
import br.com.danilo.aikotestebus.presentation.navigation.BusRoute
import br.com.danilo.aikotestebus.presentation.util.state.AuthenticatorState
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthenticatorScreen(
    navController: NavController,
    authenticatorViewModel: AuthenticatorViewModel = koinViewModel()
) {
    val uiState by authenticatorViewModel.uiState.collectAsState()

    when (uiState) {
        is AuthenticatorState.InitialState -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { authenticatorViewModel.fetchAuthenticator() }
                ) {
                    Text(text = "Iniciar")
                }
            }
        }

        is AuthenticatorState.Success -> {
            LaunchedEffect(Unit) {
                navController.navigate(BusRoute.BusTabContainer.route) {
                    popUpTo(BusRoute.BusAuthenticator.route) { inclusive = true }
                }
            }
        }

        is AuthenticatorState.Error -> {

        }

        AuthenticatorState.Loading -> {
            Loading()
        }
    }
}