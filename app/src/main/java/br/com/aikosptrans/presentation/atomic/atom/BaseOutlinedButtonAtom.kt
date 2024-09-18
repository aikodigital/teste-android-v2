package br.com.aikosptrans.presentation.atomic.atom

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.aikosptrans.ui.theme.Colors
import br.com.aikosptrans.ui.theme.Typography

@Composable
fun BaseOutlinedButton(
    onClick: () -> Unit,
    label: String
) {
    OutlinedButton(
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.Transparent,
            contentColor = Colors.black
        ),
        shape = RoundedCornerShape(32.dp),
        border = BorderStroke(
            width = 1.dp,
            color = Colors.blue
        ),
    ) {
        Text(
            text = label,
            style = Typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}

