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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import hopeapps.dedev.sptrans.R
import hopeapps.dedev.sptrans.ui.theme.Dimens

@Composable
fun BusListItem(
    modifier: Modifier = Modifier,
    firstLabel: String? = "",
    secondLabel: Int? = null,
    mainTerminal: String? = "",
    secondaryTerminal: String? = "",
    isCircular: Boolean = false,
    elevation: Dp = 4.dp,
    onClickListener: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClickListener() },
        shape = RoundedCornerShape(Dimens.Dimens_16_Dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation)
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = Dimens.Dimens_16_Dp,
                vertical = Dimens.Dimens_16_Dp
            )
        ) {
            Text(
                text = firstLabel.orEmpty(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            if (secondLabel != null) {
                Spacer(modifier = Modifier.height(Dimens.Dimens_8_Dp))
                Text(
                    text = "Linha: $secondLabel",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            if (!mainTerminal.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(Dimens.Dimens_16_Dp))
                Text(
                    text = mainTerminal,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            if (!secondaryTerminal.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(Dimens.Dimens_16_Dp))
                Text(
                    text = secondaryTerminal,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(Dimens.Dimens_16_Dp))

            if (isCircular) {
                Text(
                    text = stringResource(R.string.line_is_in_circulation),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                Text(
                    text = stringResource(R.string.line_is_not_in_circulation),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BusItemPreview() {
    BusListItem(
        firstLabel = "3001",
        secondLabel = 10,
        mainTerminal = "terminal principal",
        secondaryTerminal = "terminal secundário",
        onClickListener = {}
    )
}
