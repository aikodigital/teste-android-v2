package com.leonardolino.busfinder.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.leonardolino.busfinder.R
import com.leonardolino.busfinder.domain.model.EstimatedArrival
import com.leonardolino.busfinder.presentation.state.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArrivalsBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    contentState: UiState<EstimatedArrival>,
    onListItemClick: (EstimatedArrival.Stop.Line) -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    var query by remember { mutableStateOf("") }

    ModalBottomSheet(
        modifier = modifier, sheetState = bottomSheetState, onDismissRequest = onDismissRequest
    ) {
        when (contentState) {
            is UiState.Loading -> LoadingIndicator()
            is UiState.Success -> {
                val filteredLines = contentState.data.stop?.lines?.filter { line ->
                    line.fullSign.contains(
                        query, ignoreCase = true
                    ) || line.destinationSign.contains(query, ignoreCase = true)
                }.orEmpty()

                ArrivalsContent(
                    query = query,
                    onQueryChange = { newQuery -> query = newQuery },
                    filteredLines = filteredLines,
                    onListItemClick = onListItemClick
                )
            }

            is UiState.Error -> ErrorMessage(contentState.message)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArrivalsContent(
    query: String,
    onQueryChange: (String) -> Unit,
    filteredLines: List<EstimatedArrival.Stop.Line>,
    onListItemClick: (EstimatedArrival.Stop.Line) -> Unit
) {
    LazyColumn(
        Modifier.fillMaxSize(), contentPadding = PaddingValues(vertical = 8.dp, horizontal = 20.dp)
    ) {
        item {
            Text(
                modifier = Modifier.padding(bottom = 24.dp),
                text = stringResource(R.string.next_arrivals),
                style = MaterialTheme.typography.headlineMedium
            )
        }

        item {
            SearchBar(modifier = Modifier.fillMaxWidth(),
                query = query,
                onQueryChange = onQueryChange,
                onSearch = {},
                active = false,
                onActiveChange = {},
                placeholder = { Text(text = stringResource(R.string.search_hint)) },
                leadingIcon = { Icon(Icons.Default.Search, null) }) {

            }
        }

        if (filteredLines.isEmpty()) {
            item {
                Text(
                    text = stringResource(R.string.search_no_items),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
        } else {
            items(filteredLines) { line ->
                ListItem(modifier = Modifier.clickable { onListItemClick(line) },
                    trailingContent = { Text(text = line.vehicles.first().expectedTime) },
                    headlineContent = { Text(text = line.fullSign) },
                    supportingContent = { Text(text = line.destinationSign) })
            }
        }
    }
}
