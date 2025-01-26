package br.com.danilo.aikotestebus.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_16
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_24
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_72
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_8

@Composable
fun GenericError(
    modifier: Modifier = Modifier,
    iconResId: Int,
    iconContentDescription: String? = null,
    heading: String,
    paragraph: String,
) {
    Column(
        modifier = modifier
            .padding(
                horizontal = spacing_16,
                vertical = spacing_24,
            ).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(iconResId),
            contentDescription = iconContentDescription,
            tint = Color.Black,
            modifier = Modifier.size(spacing_72),
        )
        Spacer(modifier = Modifier.height(spacing_24))
        Text(
            text = heading,
            style = MaterialTheme.typography.labelLarge,
            softWrap = true,
        )
        Spacer(modifier = Modifier.height(spacing_8))
        Text(
            text = paragraph,
            style = MaterialTheme.typography.bodySmall,
            softWrap = true,
        )
    }
}