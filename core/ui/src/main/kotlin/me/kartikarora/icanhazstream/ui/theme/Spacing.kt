package me.kartikarora.icanhazstream.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @kartikarora Brand Spacing & Layout Constraints (v2.0.0)
 * Sourced from kartikarora-compose-theme/references/Spacing.kt.
 */
data class Spacing(
    val extraSmall: Dp = 8.dp,       // --space-xs (0.5rem)
    val small: Dp = 16.dp,           // --space-sm (1.0rem)
    val medium: Dp = 24.dp,          // --space-md (1.5rem)
    val large: Dp = 32.dp,           // --space-lg (2.0rem)
    val extraLarge: Dp = 48.dp,      // --space-xl (3.0rem)
    val twoExtraLarge: Dp = 64.dp,   // --space-2xl (4.0rem)
    val threeExtraLarge: Dp = 96.dp, // --space-3xl (6.0rem)

    // Container widths
    val maxWidth: Dp = 1200.dp,      // --max-width (1200px)
    val maxWidthNarrow: Dp = 800.dp  // --max-width-narrow (800px)
)

val DefaultSpacing = Spacing()

val LocalSpacing = staticCompositionLocalOf { DefaultSpacing }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current
