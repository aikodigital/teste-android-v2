package br.com.danilo.aikotestebus.presentation.features.lines

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.danilo.aikotestebus.R
import br.com.danilo.aikotestebus.domain.model.LineDetail
import br.com.danilo.aikotestebus.presentation.components.organism.SearchFieldMolecule
import br.com.danilo.aikotestebus.presentation.util.state.LineBusDetailState
import org.koin.androidx.compose.koinViewModel

@Composable
fun LineBusDetailsScreen(
    lineBusDetailsViewModel: LineBusDetailsViewModel = koinViewModel()
) {
    val uiState by lineBusDetailsViewModel.uiState.collectAsState()
    val searchQuery by lineBusDetailsViewModel.searchQuery.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchFieldMolecule(
            label = "Digite a linha aqui",
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
                        LineDetailItem(lineDetail = it)
                        HorizontalDivider()
                    }
                }
            }

            is LineBusDetailState.Error -> {
                Text(text = "Erro ao carregar os dados.")
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
                            .size(36.dp)
                    )
                }

            }

            LineBusDetailState.ShowScreen -> {}
        }
    }
}

@Composable
fun LineDetailItem(lineDetail: LineDetail) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_bus),
            contentDescription = "Ícone de ônibus",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(48.dp)
                .padding(end = 16.dp)
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Número: ${lineDetail.firstLabelNumber}-${lineDetail.secondLabelNumber}",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Ponto Inicial: ${lineDetail.mainTerminal}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "Ponto Final: ${lineDetail.secondaryTerminal}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Em circulação: ${if (lineDetail.isCircular) "Sim" else "Não"}",
                style = MaterialTheme.typography.bodySmall.copy(fontStyle = FontStyle.Italic),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}