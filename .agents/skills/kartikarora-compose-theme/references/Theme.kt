package me.kartikarora.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

/**
 * Material 3 Light Color Scheme mapped to @kartikarora Brand Guidelines (v2.0.0).
 */
val KartikAroraLightColorScheme: ColorScheme = lightColorScheme(
    primary = KartikAroraColors.LightSignal,
    onPrimary = KartikAroraColors.LightSignalInk,
    primaryContainer = KartikAroraColors.LightPaperSunken,
    onPrimaryContainer = KartikAroraColors.LightInk,
    inversePrimary = KartikAroraColors.DarkSignal,

    secondary = KartikAroraColors.LightInkMuted,
    onSecondary = Color.White,
    secondaryContainer = KartikAroraColors.LightPaperAlt,
    onSecondaryContainer = KartikAroraColors.LightInk,

    tertiary = KartikAroraColors.LightInkFaint,
    onTertiary = Color.White,
    tertiaryContainer = KartikAroraColors.LightPaperSunken,
    onTertiaryContainer = KartikAroraColors.LightInk,

    background = KartikAroraColors.LightPaper,
    onBackground = KartikAroraColors.LightInk,

    surface = KartikAroraColors.LightPaperAlt,
    onSurface = KartikAroraColors.LightInk,
    surfaceVariant = KartikAroraColors.LightPaperSunken,
    onSurfaceVariant = KartikAroraColors.LightInkMuted,
    surfaceContainer = KartikAroraColors.LightPaperAlt,
    surfaceContainerLow = KartikAroraColors.LightPaper,
    surfaceContainerHigh = KartikAroraColors.LightPaperSunken,

    outline = KartikAroraColors.LightRule,
    outlineVariant = KartikAroraColors.LightRuleStrong,

    inverseSurface = KartikAroraColors.DarkPaper,
    inverseOnSurface = KartikAroraColors.DarkInk,

    error = KartikAroraColors.Danger,
    onError = Color.White
)

/**
 * Material 3 Dark Color Scheme mapped to @kartikarora Brand Guidelines (v2.0.0).
 */
val KartikAroraDarkColorScheme: ColorScheme = darkColorScheme(
    primary = KartikAroraColors.DarkSignal,
    onPrimary = KartikAroraColors.DarkSignalInk,
    primaryContainer = KartikAroraColors.DarkPaperSunken,
    onPrimaryContainer = KartikAroraColors.DarkInk,
    inversePrimary = KartikAroraColors.LightSignal,

    secondary = KartikAroraColors.DarkInkMuted,
    onSecondary = KartikAroraColors.DarkPaper,
    secondaryContainer = KartikAroraColors.DarkPaperAlt,
    onSecondaryContainer = KartikAroraColors.DarkInk,

    tertiary = KartikAroraColors.DarkInkFaint,
    onTertiary = KartikAroraColors.DarkInk,
    tertiaryContainer = KartikAroraColors.DarkPaperSunken,
    onTertiaryContainer = KartikAroraColors.DarkInk,

    background = KartikAroraColors.DarkPaper,
    onBackground = KartikAroraColors.DarkInk,

    surface = KartikAroraColors.DarkPaperAlt,
    onSurface = KartikAroraColors.DarkInk,
    surfaceVariant = KartikAroraColors.DarkPaperSunken,
    onSurfaceVariant = KartikAroraColors.DarkInkMuted,
    surfaceContainer = KartikAroraColors.DarkPaperAlt,
    surfaceContainerLow = KartikAroraColors.DarkPaper,
    surfaceContainerHigh = KartikAroraColors.DarkRule,

    outline = KartikAroraColors.DarkRule,
    outlineVariant = KartikAroraColors.DarkRuleStrong,

    inverseSurface = KartikAroraColors.LightPaper,
    inverseOnSurface = KartikAroraColors.LightInk,

    error = KartikAroraColors.Danger,
    onError = KartikAroraColors.DarkPaper
)

/**
 * Root Composable Theme for @kartikarora brand design system (v2.0.0).
 * Works across Android and Compose Multiplatform (iOS, Desktop, Web).
 */
@Composable
fun KartikAroraTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) KartikAroraDarkColorScheme else KartikAroraLightColorScheme

    CompositionLocalProvider(
        LocalSpacing provides DefaultSpacing
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = KartikAroraTypography,
            shapes = KartikAroraShapes,
            content = content
        )
    }
}
