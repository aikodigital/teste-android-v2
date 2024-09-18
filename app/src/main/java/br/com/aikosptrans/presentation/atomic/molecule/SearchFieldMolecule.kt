package br.com.aikosptrans.presentation.atomic.molecule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.aikosptrans.R
import br.com.aikosptrans.ui.theme.Colors

@Composable
fun SearchFieldMolecule(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    onSearchPressed: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .background(Colors.white)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChanged,
            label = {
                Text(
                    text = label,
                    fontSize = 10.sp
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Colors.black,
                unfocusedIndicatorColor = Colors.black,
                textColor = Colors.black,
                focusedLabelColor = Colors.black,
                unfocusedLabelColor = Colors.black,
            ),
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
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null
                )
            },
        )
    }
}