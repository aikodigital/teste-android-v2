package br.com.danilo.aikotestebus.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp
import br.com.danilo.aikotestebus.R
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_12
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_16
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_20
import br.com.danilo.aikotestebus.presentation.util.Spacing.spacing_8

@Composable
fun SearchField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    onSearchPressed: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = spacing_16, vertical = spacing_8)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChanged,
            label = {
                Text(
                    text = label,
                    fontSize = 12.sp,
                    color = Color.Gray,
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchPressed(value)
                }
            ),
            singleLine = true,
            trailingIcon = {
                SearchIcon { onSearchPressed(value) }
            },
            shape = RoundedCornerShape(spacing_12),
        )
    }
}

@Composable
fun SearchIcon(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            painter = painterResource(R.drawable.ic_search),
            contentDescription = "Buscar",
            tint = Color.Black,
            modifier = Modifier.size(spacing_20)
        )
    }
}