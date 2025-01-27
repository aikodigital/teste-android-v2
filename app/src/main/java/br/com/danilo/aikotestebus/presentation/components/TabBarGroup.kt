package br.com.danilo.aikotestebus.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import br.com.danilo.aikotestebus.presentation.util.ONE
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_2
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_8
import br.com.danilo.aikotestebus.presentation.util.ZERO
import br.com.danilo.aikotestebus.ui.theme.colorsMain

@Composable
fun TabBarGroupScrollable(
    modifier: Modifier = Modifier,
    tabs: List<String>,
    tabIndex: Int = ZERO,
    onTabSelected: (tabIndex: Int) -> Unit,
) {
    val selectedTabIndex by remember(tabIndex) { mutableIntStateOf(tabIndex) }
    TabRow(
        modifier = modifier.fillMaxWidth(),
        selectedTabIndex = selectedTabIndex,
        containerColor = colorsMain.inactiveContent,
        indicator = { tabPositions ->
            TabBarGroupIndicator(tabPositions, selectedTabIndex)
        },
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                modifier = Modifier
                    .fillMaxWidth(ONE.toFloat()),
                text = {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyMedium,
                        softWrap = true,
                        color = colorsMain.text,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(spacing_8),
                        textAlign = TextAlign.Center,
                    )
                },
                selected = selectedTabIndex == index,
                onClick = {
                    onTabSelected(index)
                },
                selectedContentColor = colorsMain.tabSelected,
                unselectedContentColor = colorsMain.inactiveContent,
            )
        }
    }
}

@Composable
private fun TabBarGroupIndicator(
    tabPositions: List<TabPosition>,
    selectedTabIndex: Int,
) {
    tabPositions.forEachIndexed { index, tabPosition ->
        val color =
            if (index == selectedTabIndex) {
                colorsMain.tabSelected
            } else {
                colorsMain.tabUnselected
            }
        SecondaryIndicator(
            modifier = Modifier.tabIndicatorOffset(tabPosition),
            height = spacing_2,
            color = color,
        )
    }
}