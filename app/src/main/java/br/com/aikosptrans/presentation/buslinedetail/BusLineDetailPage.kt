package br.com.aikosptrans.presentation.buslinedetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.aikosptrans.R
import br.com.aikosptrans.domain.entity.BusLine
import br.com.aikosptrans.presentation.atomic.organism.ArriveTimeOrganism
import br.com.aikosptrans.presentation.atomic.organism.DialogMessageOrganism
import br.com.aikosptrans.presentation.atomic.template.BottomSheetDialogTemplate
import br.com.aikosptrans.presentation.atomic.template.BusLineDetailTemplate
import br.com.aikosptrans.presentation.buslinedetail.viewmodel.BusLineDetailViewModel
import br.com.aikosptrans.presentation.navigation.AppDestination
import org.koin.androidx.compose.koinViewModel

@Composable
fun BusLineDetailPage(
    navController: NavController,
    busLine: BusLine?,
    viewModel: BusLineDetailViewModel = koinViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getBusStopByLine(busLine?.lineId.toString())
    }

    if(uiState.hasError) {
        DialogMessageOrganism(
            title = stringResource(R.string.error),
            message = stringResource(R.string.try_again_message),
            mainButtonText = stringResource(R.string.try_again),
            mainButtonAction = {
                viewModel.clearError()
                viewModel.getBusStopByLine(busLine?.lineId.toString())
            },
            auxiliaryButtonAction = viewModel::clearError,
            auxiliaryButtonText = stringResource(R.string.cancel)
        )
    }

    BottomSheetDialogTemplate(
        navController = navController,
        show = uiState.shouldShowTime,
        sheetContent = {
            ArriveTimeOrganism(
                arriveForecastTime = uiState.arriveForecastTime
            )
        },
        dismissCallBack = viewModel::clearBottomSheetVisibility,
        content = {
            BusLineDetailTemplate(
                busLine = busLine,
                busStops = uiState.busStops,
                onItemClicked = {
                    viewModel.getArriveForecastTime(
                        idLine = busLine?.lineId.toString(),
                        idStop = it.busStopId.toString()
                    )
                },
                onBackClicked = {
                    navController.navigate(AppDestination.BusLine.createRoute())
                }
            )
        }
    )
}