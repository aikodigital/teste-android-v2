package br.com.danilo.aikotestebus.presentation.features.authenticator

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.danilo.aikotestebus.R
import br.com.danilo.aikotestebus.presentation.components.Loading
import br.com.danilo.aikotestebus.presentation.navigation.BusRoute
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_16
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_180
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_32
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_40
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_72
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

                Image(
                    painter = painterResource(R.drawable.aiko),
                    contentDescription = "Ícone da aiko",
                    modifier = Modifier
                        .size(spacing_180)
                        .padding(bottom = spacing_72)
                )
                Button(
                    onClick = { authenticatorViewModel.fetchAuthenticator() }
                ) {
                    Text(text = stringResource(R.string.bus_authenticator_button))
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

        is AuthenticatorState.Error -> {}

        AuthenticatorState.Loading -> {
            Loading()
        }
    }
}