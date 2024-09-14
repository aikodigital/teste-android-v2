package br.com.aikosptrans.presentation.atomic.template

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.aikosptrans.R
import br.com.aikosptrans.presentation.atomic.ImageAtom
import br.com.aikosptrans.ui.theme.BaseTheme
import br.com.aikosptrans.ui.theme.Colors

@Composable
fun SplashTemplate(
    modifier: Modifier = Modifier
) {

    Column (
        modifier = modifier
            .fillMaxSize()
            .background(Colors.white),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageAtom(
            image = painterResource(R.drawable.aiko_logo),
            modifier = modifier
                .height(95.dp)
                .width(190.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        CircularProgressIndicator(color = Colors.blue)
    }
}

@Preview
@Composable
private fun SplashTemplatePreview() {
    BaseTheme {
        SplashTemplate()
    }
}