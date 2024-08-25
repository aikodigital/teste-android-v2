package com.leonardolino.busfinder.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.leonardolino.busfinder.domain.model.NextArrivals
import com.leonardolino.busfinder.presentation.state.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArrivalsBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    contentState: UiState<NextArrivals>
) {
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    var query by remember { mutableStateOf("") }

    ModalBottomSheet(
        modifier = modifier,
        sheetState = bottomSheetState,
        onDismissRequest = onDismissRequest
    ) {
        when (contentState) {
            UiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is UiState.Success -> {
                contentState.data.p?.let { stop ->
                    val filteredLines = stop.l.filter { line ->
                        line.c.contains(query, ignoreCase = true) || line.lt0.contains(
                            query,
                            ignoreCase = true
                        )
                    }

                    LazyColumn(
                        Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 20.dp)
                    ) {
                        item {
                            Text(
                                modifier = Modifier.padding(bottom = 24.dp),
                                text = "Próximas chegadas",
                                style = MaterialTheme.typography.headlineMedium
                            )
                        }

                        item {
                            SearchBar(modifier = Modifier.fillMaxWidth(),
                                query = query,
                                onQueryChange = { newQuery -> query = newQuery },
                                onSearch = {},
                                active = false,
                                onActiveChange = { },
                                placeholder = { Text(text = "Pesquise o nome ou número da linha") },
                                leadingIcon = { Icon(Icons.Default.Search, null) }) {

                            }
                        }

                        if (filteredLines.isEmpty()) {
                            item {
                                Text(
                                    text = "Nenhum resultado encontrado",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(vertical = 16.dp)
                                )
                            }
                        } else {
                            items(filteredLines) { line ->
                                ListItem(trailingContent = { Text(text = line.vs.first().t) },
                                    headlineContent = { Text(text = line.c) },
                                    supportingContent = { Text(text = line.lt0) })
                            }
                        }
                    }
                } ?: Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Não há previsão de chegada. Tente novamente mais tarde.")
                }
            }

            is UiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = contentState.message)
                }
            }
        }
    }
}