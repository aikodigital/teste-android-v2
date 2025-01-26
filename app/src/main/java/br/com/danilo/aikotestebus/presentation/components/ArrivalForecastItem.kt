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
import br.com.danilo.aikotestebus.R
import br.com.danilo.aikotestebus.domain.model.ArrivalForecastBus
import br.com.danilo.aikotestebus.domain.model.ArrivalForecastRelation
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_12
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_16
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_24
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_40
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_48
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_8

@Composable
fun ArrivalForecastItem(
    arrivalForecast: ArrivalForecastRelation,
    clickedItem: (ArrivalForecastBus) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(spacing_12)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(spacing_8)
            )
            .padding(spacing_16),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_map),
            contentDescription = "Ícone de mapa",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(spacing_48)
                .padding(end = spacing_24)
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(
                    R.string.bus_arrival_forecast_item_title,
                    arrivalForecast.letterComplete
                ),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(spacing_8))
            Text(
                text = stringResource(
                    R.string.bus_arrival_forecast_time_subtitle_one,
                    arrivalForecast.lineOrigin
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = stringResource(
                    R.string.bus_arrival_forecast_time_subtitle_two,
                    arrivalForecast.lineDestination
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            arrivalForecast.buses.forEach { bus ->
                Spacer(modifier = Modifier.height(spacing_12))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { clickedItem.invoke(bus) }
                        .padding(vertical = spacing_8),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_bus),
                        contentDescription = "Ícone de ônibus",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(spacing_24)
                            .padding(end = spacing_16)
                    )

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = stringResource(
                                R.string.bus_arrival_forecast_time_bus_subtitle_one,
                                bus.arrivalForecastTime
                            ),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = stringResource(
                                R.string.bus_arrival_forecast_time_bus_subtitle_two,
                                bus.prefixNumber
                            ),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = stringResource(
                                R.string.bus_arrival_forecast_time_bus_subtitle_three,
                                if (bus.isAccessible) stringResource(R.string.yes) else stringResource(R.string.no)
                            ),
                            style = MaterialTheme.typography.bodySmall.copy(fontStyle = FontStyle.Italic),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Icon(
                        painter = painterResource(R.drawable.chevron_right_24px),
                        contentDescription = "Ícone de ônibus",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(spacing_40)
                            .padding(start = spacing_16)
                    )
                }
            }
        }
    }
}