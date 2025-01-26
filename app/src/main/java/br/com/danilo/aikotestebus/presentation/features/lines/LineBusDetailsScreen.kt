package br.com.danilo.aikotestebus.presentation.features.lines

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import br.com.danilo.aikotestebus.R
import br.com.danilo.aikotestebus.presentation.components.GenericError
import br.com.danilo.aikotestebus.presentation.components.LineDetailItem
import br.com.danilo.aikotestebus.presentation.components.SearchField
import br.com.danilo.aikotestebus.presentation.navigation.BusRoute
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_36
import br.com.danilo.aikotestebus.presentation.util.encodeLineDetailItem
import br.com.danilo.aikotestebus.presentation.util.state.LineBusDetailState
import org.koin.androidx.compose.koinViewModel

@Composable
fun LineBusDetailsScreen(
    navController: NavController,
    lineBusDetailsViewModel: LineBusDetailsViewModel = koinViewModel()
) {
    val uiState by lineBusDetailsViewModel.uiState.collectAsState()
    val searchQuery by lineBusDetailsViewModel.searchQuery.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchField(
            label = stringResource(R.string.bus_line_details_search_label),
            value = searchQuery,
            onValueChanged = {
                lineBusDetailsViewModel.onSearchQueryChange(it)
            }) {
            lineBusDetailsViewModel.onSearchQueryChange(it)
        }

        when (uiState) {
            is LineBusDetailState.Success -> {
                val data = (uiState as LineBusDetailState.Success).items
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(data) {
                        LineDetailItem(lineDetail = it, true) { lineDetail ->
                            navController.navigate(
                                BusRoute.BusStopByLine.route.replace(
                                    "{item}", encodeLineDetailItem(lineDetail)
                                )
                            )
                        }
                        HorizontalDivider()
                    }
                }
            }

            is LineBusDetailState.Error -> {
                Column(Modifier.fillMaxSize()) {
                    GenericError(
                        iconResId = R.drawable.sentiment_dissatisfied_24px,
                        heading = stringResource(R.string.bus_line_details_error_heading),
                        paragraph = stringResource(R.string.bus_line_details_error_paragraph)
                    )
                }
            }

            LineBusDetailState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        color = Color.Black,
                        modifier = Modifier
                            .size(spacing_36)
                    )
                }

            }

            LineBusDetailState.ShowScreen -> {}
        }
    }
}