package br.com.aikosptrans.presentation.atomic.template

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import br.com.aikosptrans.R
import br.com.aikosptrans.domain.entity.BusLine
import br.com.aikosptrans.presentation.atomic.molecule.SearchFieldMolecule
import br.com.aikosptrans.presentation.atomic.organism.BottomNavigationOrganism
import br.com.aikosptrans.presentation.atomic.organism.BusLineItemOrganism

@Composable
fun BusLineTemplate(
    navController: NavController,
    lines: List<BusLine>,
    query: String,
    onSearchPressed: (String) -> Unit,
    onQueryChanged: (String) -> Unit,
    onItemClicked: (BusLine) -> Unit
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SearchFieldMolecule(
                label = stringResource(R.string.bus_lines_hint),
                value = query,
                onValueChanged = onQueryChanged,
                onSearchPressed = onSearchPressed
            )
        },
        bottomBar = {
            BottomNavigationOrganism(navController)
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
        ) {
            LazyColumn {
                items(lines) {
                    BusLineItemOrganism(
                        line = it,
                        onItemClicked = onItemClicked
                    )
                }
            }
        }
    }
}