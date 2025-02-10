package hopeapps.dedev.sptrans.presentation.screens.maps

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hopeapps.dedev.sptrans.domain.models.DynamicPoint
import hopeapps.dedev.sptrans.presentation.design_system.components.DynamicPointItem
import hopeapps.dedev.sptrans.presentation.design_system.theme.Dimens
import org.koin.androidx.compose.koinViewModel

@Composable
fun MapsOverviewScreenRoot(
    navigateToBack: () -> Unit,
    viewModel: OverviewMapsViewModel = koinViewModel()
) {
    MapsOverviewScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                is MapsOverviewAction.NavigateToBack -> navigateToBack()
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapsOverviewScreen(
    state: OverviewMapsState,
    onAction: (MapsOverviewAction) -> Unit
) {
    var isSheetOpen by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    var dynamicPoint by remember {
        mutableStateOf(
            DynamicPoint(
                latitude = 0.0,
                longitude = 0.0,
                id = 1,
                name = "Ônibus 123",
                lastUpdate = "10:30",
                accessible = true,
                prefix = 1234
            )
        )
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onAction.invoke(MapsOverviewAction.NavigateToBack)
                }
            ) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
            }
        }
    ) { padding ->
        Surface(
            modifier = if (isSheetOpen) {
                Modifier
                    .padding(
                        PaddingValues(
                            bottom = padding.calculateBottomPadding(),
                            top = Dimens.Dimens_16_Dp,
                            start = Dimens.Dimens_16_Dp,
                            end = Dimens.Dimens_16_Dp
                        )
                    )
            } else Modifier.padding(0.dp)
        ) {
            if (isSheetOpen) {
                ModalBottomSheet(
                    onDismissRequest = { isSheetOpen = false },
                    sheetState = sheetState
                ) {
                    DynamicPointItem(
                        dynamicPoint = dynamicPoint
                    )
                }
            }

            TrackerMap(
                currentFocus = state.focusLocation,
                dynamicPoints = state.dynamicPoints as List<DynamicPoint>,
                busStopLocation = state.busStopLocation,
                openBottomSheetDialog = {
                    dynamicPoint = it
                    isSheetOpen = !isSheetOpen
                },
            )
        }
    }
}

