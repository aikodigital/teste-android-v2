package br.com.danilo.aikotestebus.presentation.features

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import br.com.danilo.aikotestebus.presentation.components.organism.TabBarGroupScrollable
import br.com.danilo.aikotestebus.presentation.features.lines.LineBusDetailsScreen
import br.com.danilo.aikotestebus.presentation.features.maplocation.MapLocationBusScreen

@Composable
fun TabScreen(navController: NavController) {
    var tabIndexParam by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TabBarGroupScrollable(
            modifier = Modifier.fillMaxWidth(),
            tabs = getTabs(),
            tabIndex = tabIndexParam,
            onTabSelected = { index ->
                tabIndexParam = index
            }
        )

        when(tabIndexParam) {
            0 -> MapLocationBusScreen()
            1 -> LineBusDetailsScreen(navController = navController)
        }
    }
}

private fun getTabs(): List<String> {
    return listOf("Ônibus", "Linhas")
}