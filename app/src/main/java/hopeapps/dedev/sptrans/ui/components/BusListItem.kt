package hopeapps.dedev.sptrans.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BusListItem(
    modifier: Modifier = Modifier,
    firstLabel: String? = "",
    secondLabel: Int?,
    mainTerminal: String? = "",
    secondaryTerminal: String? = "",
    onClickListener: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClickListener() }
    ) {
        Text("first label: $firstLabel")
        Text("second label: $secondLabel")
        Text("main terminal: $mainTerminal")
        Text("secondary terminal: $secondaryTerminal")
    }
}

@Preview(showBackground = true)
@Composable
private fun BusItemPreview() {
    BusListItem(
        firstLabel = "",
        secondLabel = 0,
        mainTerminal = "",
        secondaryTerminal = "",
        onClickListener = {}
    )
}