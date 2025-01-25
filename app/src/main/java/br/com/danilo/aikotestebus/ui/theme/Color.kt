package br.com.danilo.aikotestebus.ui.theme

import androidx.compose.ui.graphics.Color

data class ColorsMain(
    val text: Color = Color(0xFF000000),
    val buttonBackground: Color = Color(0xFF0E23AD),
    val background: Color = Color(0xFFFBFBFB),
    val inactiveBackground: Color = Color(0xFFC7C7C7),
    val inactiveContent: Color = Color(0xFFFEFEFE),
    val white: Color = Color(0xFFFEFEFE),
    val error: Color = Color(0xFFC24240),
    val warning: Color = Color(0xFFECB033),
    val success: Color = Color(0xFF90C754)
)

data class ColorsSecondary(
    val text: Color = Color(0xFFF9F6F3),
    val buttonBackground: Color = Color(0xFFE6DBCE)
)

val colorsMain = ColorsMain()
val colorsSecondary = ColorsSecondary()