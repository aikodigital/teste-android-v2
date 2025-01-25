package br.com.danilo.aikotestebus.presentation.features.lines

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import br.com.danilo.aikotestebus.presentation.components.organism.LoadingOrganism
import br.com.danilo.aikotestebus.presentation.components.organism.SearchFieldMolecule
import br.com.danilo.aikotestebus.presentation.util.state.LineBusDetailState
import org.koin.androidx.compose.koinViewModel

@Composable
fun LineBusDetailsScreen(
    lineBusDetailsViewModel: LineBusDetailsViewModel = koinViewModel()
) {
    val uiState by lineBusDetailsViewModel.uiState.collectAsState()
    val searchQuery by lineBusDetailsViewModel.searchQuery.collectAsState()

    when (uiState) {
        is LineBusDetailState.Success -> {
            val data = (uiState as LineBusDetailState.Success).item
            Text(text = "teste -> ${data.toString()}")
        }

        is LineBusDetailState.Error -> {
            Text(text = "Erro ao carregar os dados.")
        }

        LineBusDetailState.Loading -> {
            LoadingOrganism()
        }

        LineBusDetailState.ShowScreen -> {
            
            SearchFieldMolecule(label = "Digite a linha aqui", value = searchQuery, onValueChanged = {
                lineBusDetailsViewModel.fetchLines(it)
            }) {
                lineBusDetailsViewModel.fetchLines(it)
            }
        }
    }
}