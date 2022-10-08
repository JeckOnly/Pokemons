package com.jeckonly.pokemons.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// TODO 设置黑夜主题 后
private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

// TODO 设置白天主题 先
private val LightColorPalette = lightColors(
    primary = Blue8,
    primaryVariant = Blue10,
    background = Color.White,
    surface = Color.White,
    onSurface = Blue10,
    onBackground = Blue10,
    error = Red10,
    onError = Color.Black
    /* Other default colors to override
    onPrimary = Color.White,
    secondary = Teal200,
    onSecondary = Color.Black,
    */
)

@Composable
fun PokemonsTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}