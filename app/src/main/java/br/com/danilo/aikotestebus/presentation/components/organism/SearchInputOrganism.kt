package br.com.danilo.aikotestebus.presentation.components.organism

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
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.danilo.aikotestebus.R

@Composable
fun SearchFieldMolecule(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    onSearchPressed: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
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
            shape = RoundedCornerShape(12.dp),
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
            modifier = Modifier.size(20.dp)
        )
    }
}