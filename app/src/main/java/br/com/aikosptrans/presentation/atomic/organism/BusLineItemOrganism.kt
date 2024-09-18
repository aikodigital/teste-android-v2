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
import br.com.aikosptrans.domain.entity.BusLine

@Composable
fun BusLineItemOrganism(
    line: BusLine,
    onItemClicked: (BusLine) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                onItemClicked(line)
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_bus_line),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ){
            Text(
                text = stringResource(R.string.bus_lines_number, line.fullNumber),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = stringResource(R.string.bus_lines_origin, line.origin),
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
            Text(
                text = stringResource(R.string.bus_lines_destination, line.destination),
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
            Text(
                text = stringResource(
                    R.string.bus_lines_circular,
                    if(line.isCircular == true)
                        stringResource(R.string.yes)
                    else
                        stringResource(R.string.no)
                ),
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
    BusLineItemOrganism(
        line = BusLine(
            fullNumber = "80000-10",
            destination = "PCA. RAMOS DE AZEVEDO",
            origin = "TERMINAL LAPA",
            flow = 1,
            lineId = 23123123,
            isCircular = true
        ),
        onItemClicked = {}
    )
}