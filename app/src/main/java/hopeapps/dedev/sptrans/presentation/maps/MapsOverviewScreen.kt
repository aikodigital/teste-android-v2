package hopeapps.dedev.sptrans.presentation.maps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hopeapps.dedev.sptrans.domain.models.Location
import org.koin.androidx.compose.koinViewModel

@Composable
fun MapsOverviewScreenRoot(
//    navigateToSearch: () -> Unit,
    viewModel: OverviewMapsViewModel = koinViewModel()
) {
    MapsOverviewScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
//                MapsOverviewAction.NavigateSearch -> navigateToSearch()
//                MapsOverviewAction.NavigateSearch -> {
//
//                }

                else -> {}
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

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
//                    onAction.invoke(MapsOverviewAction.NavigateSearch)
                }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
            }
        }
    ) { padding ->
        Surface(
            if (isSheetOpen) {
                Modifier
                    .padding(
                        PaddingValues(
                            bottom = padding.calculateBottomPadding(),
                            top = 0.dp,
                            start = 0.dp,
                            end = 0.dp
                        )
                    )
            } else Modifier.padding(0.dp)
        ) {
            if (isSheetOpen) {
                ModalBottomSheet(
                    onDismissRequest = { isSheetOpen = false },
                    sheetState = sheetState
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text("Este é um Bottom Sheet", style = MaterialTheme.typography.titleMedium)
                        Button(onClick = { isSheetOpen = false }) {
                            Text("Fechar")
                        }
                    }
                }
            }

            TrackerMap(
                isRunFinished = false,
                currentLocation = Location(
                    lat = -23.550520,
                    long = -46.633309
                ),
                locations = emptyList(),
                onSnapshot = {},
                openBottomSheetDialog = {
                    isSheetOpen = !isSheetOpen
                }
            )
        }
    }
}
