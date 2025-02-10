package hopeapps.dedev.sptrans.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import hopeapps.dedev.sptrans.R
import hopeapps.dedev.sptrans.ui.theme.Dimens.Dimens_12_Dp
import hopeapps.dedev.sptrans.ui.theme.Dimens.Dimens_16_Dp
import hopeapps.dedev.sptrans.ui.theme.Dimens.Dimens_4_Dp

@Composable
fun PredictionListItem(
    predictionTime: String,
    origin: String,
    destination: String,
    accessibleVehicle: Boolean,
    lastUpdateTime: String,
    onClickListener: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens_4_Dp)
            .clickable { onClickListener() },
        shape = RoundedCornerShape(Dimens_12_Dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .padding(Dimens_16_Dp)
        ) {
            Text(
                text = "$origin → $destination",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(Dimens_4_Dp))

            Text(
                text = stringResource(R.string.prediction, predictionTime),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (accessibleVehicle) {
                Spacer(modifier = Modifier.height(Dimens_4_Dp))
                Text(
                    text = stringResource(R.string.accessible_vehicle),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(Dimens_4_Dp))

            Text(
                text = stringResource(R.string.last_update, lastUpdateTime),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}