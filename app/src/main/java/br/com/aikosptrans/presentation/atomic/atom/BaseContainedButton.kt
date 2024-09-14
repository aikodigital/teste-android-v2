package br.com.aikosptrans.presentation.atomic.atom

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.aikosptrans.ui.theme.Colors
import br.com.aikosptrans.ui.theme.Typography

@Composable
fun BaseContainedButton(
    onClick: () -> Unit,
    label: String
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Colors.blue,
            contentColor = Colors.white
        ),
        shape = RoundedCornerShape(32.dp)
    ) {
        Text(
            text = label,
            style = Typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}