package me.kartikarora.icanhazstream.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = StreamPrimary,
    secondary = StreamSecondary,
    tertiary = StreamAccent,
    background = StreamBackground,
    surface = StreamSurface,
    onPrimary = StreamOnPrimary,
    onBackground = StreamOnBackground,
    outline = StreamBorder,
)

private val DarkColorScheme = darkColorScheme(
    primary = StreamPrimary,
    secondary = StreamSecondary,
    tertiary = StreamAccent,
    background = StreamDarkBackground,
    surface = StreamDarkSurface,
    onPrimary = StreamOnPrimary,
    onBackground = StreamOnDarkBackground,
    outline = StreamDarkBorder,
)

@Composable
fun ICanHazStreamTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = StreamTypography,
        content = content,
    )
}
