package br.com.aikosptrans.presentation.atomic

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.aikosptrans.R

@Composable
fun ImageAtom(
    modifier: Modifier = Modifier,
    image: Painter,
    colorFilter: ColorFilter? = null,
    contentDescription: String? = null,
) {
    Image(
        painter = image,
        modifier = modifier,
        contentScale = ContentScale.Fit,
        contentDescription = contentDescription,
        colorFilter = colorFilter
    )
}

@Preview
@Composable
private fun ImageAtomPreview() {
    ImageAtom(
        image = painterResource(R.drawable.aiko_logo)
    )
}