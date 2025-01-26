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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.danilo.aikotestebus.R
import br.com.danilo.aikotestebus.domain.model.StopDetail
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_12
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_16
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_24
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_48
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_8

@Composable
fun StopDetailItem(
    stopDetail: StopDetail,
    clickedItem: (StopDetail) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(spacing_12)
            .background(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .padding(spacing_16)
            .clickable { clickedItem.invoke(stopDetail) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_point_24),
            contentDescription = "Ícone de ônibus",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(spacing_48)
                .padding(end = spacing_24)
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.bus_stop_detail_item_title, stopDetail.name),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(spacing_8))
            if (stopDetail.address.isNotEmpty()) {
                Text(
                    text = stringResource(
                        R.string.bus_stop_detail_item_subtitle,
                        stopDetail.address
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}