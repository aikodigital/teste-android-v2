package br.com.danilo.aikotestebus.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.danilo.aikotestebus.R
import br.com.danilo.aikotestebus.domain.model.LineDetail
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_16
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_2
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_24
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_48
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_8

@Composable
fun LineDetailItem(
    lineDetail: LineDetail,
    clickedItem: (LineDetail) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(spacing_2)
            .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .padding(spacing_16)
            .clickable {
                clickedItem.invoke(lineDetail)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_bus),
            contentDescription = "Ícone de ônibus",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(spacing_48)
                .padding(end = spacing_24)
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(
                    R.string.bus_line_detail_title,
                    lineDetail.firstLabelNumber,
                    lineDetail.secondLabelNumber
                ),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(spacing_8))
            Text(
                text = stringResource(
                    R.string.bus_line_detail_subtitle_one,
                    lineDetail.mainTerminal
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = stringResource(
                    R.string.bus_line_detail_subtitle_two,
                    lineDetail.secondaryTerminal
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(spacing_8))
            Text(
                text = stringResource(
                    R.string.bus_line_detail_subtitle_three,
                    if (lineDetail.isCircular) stringResource(R.string.yes) else stringResource(R.string.no)
                ),
                style = MaterialTheme.typography.bodySmall.copy(fontStyle = FontStyle.Italic),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}