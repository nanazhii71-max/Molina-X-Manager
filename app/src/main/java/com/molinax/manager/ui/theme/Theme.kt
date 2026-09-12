package com.molinax.manager.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val MolinaXDarkColorScheme = darkColorScheme(
    primary = MolinaXCyan,
    secondary = MolinaXViolet,
    background = MolinaXBackground,
    surface = MolinaXSurface,
    onPrimary = Color(0xFF00131A),
    onSecondary = Color(0xFF1B1030),
    onBackground = MolinaXOnDark,
    onSurface = MolinaXOnDark,
)

private val MolinaXLightColorScheme = lightColorScheme(
    primary = MolinaXCyan,
    secondary = MolinaXViolet,
    background = Color(0xFFFAFAFC),
    surface = Color(0xFFFFFFFF),
)

@Composable
fun MolinaXManagerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) MolinaXDarkColorScheme else MolinaXLightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
    )
}
