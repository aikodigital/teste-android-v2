package br.com.aikosptrans.presentation.atomic.organism

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.aikosptrans.R
import br.com.aikosptrans.domain.entity.BusStop

@Composable
fun BusStopItemOrganism(
    busStop: BusStop,
    onItemClicked: (BusStop) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                onItemClicked(busStop)
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_stop_bus),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ){
            Text(
                text = stringResource(R.string.bus_stop_name, busStop.name),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = stringResource(R.string.bus_stop_address, busStop.address),
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            painter = painterResource(R.drawable.ic_arrow),
            contentDescription = null
        )
    }
}

@Preview
@Composable
private fun BusLineItemOrganismPreview() {
    BusStopItemOrganism(
        busStop = BusStop(
            name = "AFONSO BRAZ B/C1",
            address = "R ARMINDA/ R BALTHAZAR DA VEIGA",
            busStopId = 23123123,
            latitude = 2000.0,
            longitude = 2000.0
        ),
        onItemClicked = {}
    )
}