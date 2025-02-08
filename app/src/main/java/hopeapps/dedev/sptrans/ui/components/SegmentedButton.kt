package hopeapps.dedev.sptrans.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MySegmentedButton(
    modifier: Modifier = Modifier,
    selectedOption: Boolean,
    firstLabel: String = "First",
    secondLabel: String = "Second",
    onFirstClick: () -> Unit = {},
    onSecondClick: () -> Unit = {}
) {
    Row(modifier) {
        SegmentedButtonItem(
            label = firstLabel,
            isSelected = selectedOption,
            onClick = onFirstClick,
            shape = RoundedCornerShape(topStart = 20.dp, bottomStart = 20.dp)
        )
        SegmentedButtonItem(
            label = secondLabel,
            isSelected = !selectedOption,
            onClick = onSecondClick,
            shape = RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp)
        )
    }
}

@Composable
private fun RowScope.SegmentedButtonItem(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    shape: Shape
) {
    Row(
        modifier = Modifier
            .weight(1f)
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                shape
            )
            .clip(shape)
            .background(
                if (isSelected) MaterialTheme.colorScheme.primaryContainer
                else MaterialTheme.colorScheme.surface
            )
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedVisibility(visible = isSelected) {
            Icon(
                modifier = Modifier.padding(end = 8.dp),
                imageVector = Icons.Outlined.Check,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Text(
            modifier = Modifier.padding(14.dp),
            text = label,
            color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer
            else MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
private fun SegmentedButtonPreview() {
    MySegmentedButton(
        selectedOption = true
    )
}