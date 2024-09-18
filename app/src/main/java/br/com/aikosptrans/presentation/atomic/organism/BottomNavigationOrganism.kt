package br.com.aikosptrans.presentation.atomic.organism

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import br.com.aikosptrans.domain.entity.BottomNavItem
import br.com.aikosptrans.ui.theme.Colors

@Composable
fun BottomNavigationOrganism(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Colors.white
    ) {
        BottomNavItem.all().forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                alwaysShowLabel = true,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        modifier = Modifier.padding(10.dp),
                        painter = painterResource(item.icon),
                        contentDescription = null
                    )
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = Colors.blueDark,
                    unselectedIconColor = Colors.gray,
                    disabledIconColor = Colors.gray,
                    disabledTextColor = Colors.gray,
                    selectedIndicatorColor = Colors.blue.copy(alpha = 0.3f),
                    selectedTextColor = Colors.blueDark,
                    unselectedTextColor = Colors.gray
                ),

                label = {
                    Text(
                        modifier = Modifier.padding(10.dp),
                        text = stringResource(item.label),
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    }
}