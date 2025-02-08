package hopeapps.dedev.sptrans.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BusListItem(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text("Número de linha: 1000")
        Text("Origem: X")
        Text("Destino: Y")
    }
}

@Preview(showBackground = true)
@Composable
private fun BusItemPreview() {
    BusListItem()
}