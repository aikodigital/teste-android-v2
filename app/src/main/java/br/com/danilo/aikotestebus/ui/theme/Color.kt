package br.com.danilo.aikotestebus.ui.theme

import androidx.compose.ui.graphics.Color

data class ColorsMain(
    val text: Color = Color(0xFF6D6262),
    val buttonBackground: Color = Color(0xFF494444),
    val background: Color = Color(0xFFFBFBFB),
    val inactiveBackground: Color = Color(0xFF5A5959),
    val inactiveContent: Color = Color(0xFFD6D3D3),
    val white: Color = Color(0xFFFEFEFE),
    val tabSelected: Color = Color(0xFF1C33A3),
    val tabUnselected: Color = Color(0xFF5A5959),
    val tabIndicator: Color = Color(0xFF1737D8)
)

data class ColorsSecondary(
    val text: Color = Color(0xFFF9F6F3),
    val buttonBackground: Color = Color(0xFFE6DBCE)
)

val colorsMain = ColorsMain()
val colorsSecondary = ColorsSecondary()