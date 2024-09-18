package br.com.aikosptrans.presentation.atomic.organism

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.aikosptrans.R
import br.com.aikosptrans.domain.entity.BusLine

@Composable
fun BusLineDetailHeaderOrganism(
    line: BusLine
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.bus_lines_number, line.fullNumber),
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = stringResource(R.string.bus_lines_origin, line.origin),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
        Text(
            text = stringResource(R.string.bus_lines_destination, line.destination),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
        Text(
            text = stringResource(
                R.string.bus_lines_circular,
                if (line.isCircular == true)
                    stringResource(R.string.yes)
                else
                    stringResource(R.string.no)
            ),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    }
}