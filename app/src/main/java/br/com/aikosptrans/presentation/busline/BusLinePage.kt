package br.com.aikosptrans.presentation.busline

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import br.com.aikosptrans.R
import br.com.aikosptrans.presentation.atomic.organism.DialogLoadingOrganism
import br.com.aikosptrans.presentation.atomic.organism.DialogMessageOrganism
import br.com.aikosptrans.presentation.atomic.template.BusLineTemplate
import br.com.aikosptrans.presentation.busline.viewmodel.BusLineViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BusLinePage(
    navController: NavController,
    viewModel: BusLineViewModel = koinViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    if(uiState.isLoading) {
        DialogLoadingOrganism()
    }

    if(uiState.hasError) {
        DialogMessageOrganism(
            title = stringResource(R.string.error),
            message = stringResource(R.string.try_again_message),
            mainButtonText = stringResource(R.string.try_again),
            mainButtonAction = {
                viewModel.clearError()
                viewModel.getBusLine(uiState.query)
            },
            auxiliaryButtonAction = viewModel::clearError,
            auxiliaryButtonText = stringResource(R.string.cancel)
        )
    }

    BusLineTemplate(
        navController = navController,
        lines = uiState.lines,
        onQueryChanged = viewModel::onQueryChanged,
        onSearchPressed = viewModel::getBusLine,
        query = uiState.query
    )
}