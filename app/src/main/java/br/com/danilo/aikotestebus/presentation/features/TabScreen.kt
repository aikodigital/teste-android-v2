package br.com.danilo.aikotestebus.presentation.features

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import br.com.danilo.aikotestebus.presentation.components.TabBarGroupScrollable
import br.com.danilo.aikotestebus.presentation.features.lines.LineBusDetailsScreen
import br.com.danilo.aikotestebus.presentation.features.maplocation.MapLocationBusScreen
import br.com.danilo.aikotestebus.presentation.util.ONE
import br.com.danilo.aikotestebus.presentation.util.ZERO
import com.google.android.gms.maps.model.LatLng

private const val TAB_ONE = "Mapa dos Ônibus"
private const val TAB_TWO = "Linhas"

@Composable
fun TabScreen(
    initialCoord: LatLng,
    index: Int,
    navController: NavController
) {
    var tabIndexParam by rememberSaveable { mutableIntStateOf(index) }

    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                TabBarGroupScrollable(
                    modifier = Modifier.fillMaxWidth(),
                    tabs = getTabs(),
                    tabIndex = tabIndexParam,
                    onTabSelected = { index ->
                        tabIndexParam = index
                    }
                )

                when (tabIndexParam) {
                    ZERO -> MapLocationBusScreen(initialCoord = initialCoord, isTabVisible = tabIndexParam == 0)
                    ONE -> LineBusDetailsScreen(navController = navController)
                }
            }
        }
    )
}

private fun getTabs(): List<String> {
    return listOf(TAB_ONE, TAB_TWO)
}