package br.com.danilo.aikotestebus.presentation.features

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.google.android.gms.maps.model.LatLng

@Composable
fun TabScreen(
    initialCoord: LatLng,
    index: Int,
    navController: NavController
) {
    var tabIndexParam by rememberSaveable { mutableIntStateOf(index) }

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

        when (tabIndexParam) {
            0 -> MapLocationBusScreen(initialCoord = initialCoord, isTabVisible = tabIndexParam == 0)
            1 -> LineBusDetailsScreen(navController = navController)
        }
    }
}

private fun getTabs(): List<String> {
    return listOf("Ônibus", "Linhas")
}