package com.lefpadlog.financeapp.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    onPrimary = darkOnPrimary,
    primary = darkPrimary,
    onSecondary = darkOnSecondary,
    secondary = darkSecondary,
    onTertiary = darkOnTertiary,
    tertiary = darkTertiary,
    onBackground = darkOnBackground,
    background = darkBackground,
    onSurface = darkOnSurface,
    surface = darkSurface
)

private val LightColorScheme = lightColorScheme(
    onPrimary = lightOnPrimary,
    primary = lightPrimary,
    onSecondary = lightOnSecondary,
    secondary = lightSecondary,
    onTertiary = lightOnTertiary,
    tertiary = lightTertiary,
    onBackground = lightOnBackground,
    background = lightBackground,
    onSurface = lightOnSurface,
    surface = lightSurface
)

@Composable
fun FinanceAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
            val insetsController = WindowCompat.getInsetsController(window, view)
            insetsController.isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}