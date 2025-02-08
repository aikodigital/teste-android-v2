package hopeapps.dedev.sptrans.presentation.line_bus

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hopeapps.dedev.sptrans.R
import hopeapps.dedev.sptrans.ui.components.BusListItem
import hopeapps.dedev.sptrans.ui.components.BusStopItem

@Composable
fun LineBusRoot(
    viewModel: LineBusViewModel,
    viewInMapClick: () -> Unit
) {
    LineBusScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                is LineBusAction.ViewInMapClick -> viewInMapClick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}


@Composable
fun LineBusScreen(
    state: LineBusState,
    onAction: (LineBusAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        BusListItem(
            modifier = Modifier
                .padding(
                    horizontal = 20.dp
                )
                .padding(top = 32.dp, bottom = 16.dp),
            firstLabel = "",
            secondLabel = 0,
            mainTerminal = "",
            secondaryTerminal = "",
            onClickListener = {
                onAction(LineBusAction.ViewInMapClick)
            }
        )

        ViewOnMapCard(
            onClick = {

            }
        )

        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = "Paradas da linha"
        )

        LazyColumn {
            items(state.busStopItems) {
                BusStopItem()
            }
        }
    }
}

@Composable
fun ViewOnMapCard(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            ),
            elevation = CardDefaults.elevatedCardElevation(1.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Ver no mapa",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Image(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(R.drawable.google_maps_img),
                    contentDescription = "Ver no mapa"
                )
            }
        }
    }
}

@Preview
@Composable
private fun LineBusPreview() {
    LineBusScreen(
        state = LineBusState(),
        onAction = {

        }
    )
}