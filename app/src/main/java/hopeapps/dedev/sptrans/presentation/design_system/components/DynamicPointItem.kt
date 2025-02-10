package hopeapps.dedev.sptrans.presentation.design_system.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import hopeapps.dedev.sptrans.R
import hopeapps.dedev.sptrans.domain.models.DynamicPoint
import hopeapps.dedev.sptrans.presentation.design_system.theme.Dimens
import hopeapps.dedev.sptrans.presentation.design_system.theme.Dimens.Dimens_12_Dp
import hopeapps.dedev.sptrans.presentation.design_system.theme.Dimens.Dimens_16_Dp
import hopeapps.dedev.sptrans.presentation.design_system.theme.Dimens.Dimens_4_Dp
import hopeapps.dedev.sptrans.presentation.design_system.theme.Dimens.Dimens_8_Dp

@Composable
fun DynamicPointItem(dynamicPoint: DynamicPoint, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimens_8_Dp),
        shape = RoundedCornerShape(Dimens_12_Dp),
        elevation = CardDefaults.cardElevation(defaultElevation = Dimens_4_Dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .padding(Dimens_16_Dp)
                .fillMaxWidth()
        ) {
            Text(
                text = dynamicPoint.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(Dimens_4_Dp))

            Text(
                text = "Última atualização: ${dynamicPoint.lastUpdate}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(Dimens_4_Dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = if (dynamicPoint.accessible) Icons.Default.CheckCircle else Icons.Default.Lock,
                    contentDescription = stringResource(R.string.acessible),
                    tint = if (dynamicPoint.accessible) Color.Green else Color.Red
                )
                Spacer(modifier = Modifier.width(Dimens_8_Dp))
                Text(
                    text = if (dynamicPoint.accessible) stringResource(R.string.acessible) else stringResource(
                        R.string.not_accessible
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(Dimens_8_Dp))

            Text(
                text = "ID: ${dynamicPoint.id}  •  Prefixo: ${dynamicPoint.prefix}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}