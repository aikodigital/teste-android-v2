package hopeapps.dedev.sptrans.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PredictionListItem(onClickListener: () -> Unit = {}) {
    Text(
        modifier = Modifier.clickable { onClickListener() },
        text = "Eu sou um item de previsão")
}