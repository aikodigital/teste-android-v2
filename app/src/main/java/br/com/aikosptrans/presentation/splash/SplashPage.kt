package br.com.aikosptrans.presentation.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import br.com.aikosptrans.R
import br.com.aikosptrans.presentation.atomic.organism.DialogMessageOrganism
import br.com.aikosptrans.presentation.atomic.template.SplashTemplate
import br.com.aikosptrans.presentation.navigation.AppDestination
import br.com.aikosptrans.presentation.splash.viewmodel.SplashState
import br.com.aikosptrans.presentation.splash.viewmodel.SplashViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashPage(
    navController: NavHostController,
    viewModel: SplashViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.authenticateUser()
    }

    LaunchedEffect(Unit) {
        viewModel.state.collectLatest { state ->
            when(state) {
                is SplashState.GoToHome -> {
                    navController.navigate(AppDestination.BusMap.createRoute())
                }
            }
        }
    }

    if(uiState.hasError) {
        DialogMessageOrganism(
            title = stringResource(R.string.error),
            message = stringResource(R.string.try_again_message),
            mainButtonText = stringResource(R.string.try_again),
            mainButtonAction = {
                viewModel.clearError()
                viewModel.authenticateUser()
            },
            auxiliaryButtonAction = viewModel::clearError,
            auxiliaryButtonText = stringResource(R.string.cancel)
        )
    }

    SplashTemplate()
}