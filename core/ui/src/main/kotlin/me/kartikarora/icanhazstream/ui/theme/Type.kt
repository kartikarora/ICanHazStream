package me.kartikarora.icanhazstream.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import me.kartikarora.icanhazstream.ui.R

/**
 * @kartikarora Brand Typography definition (v2.0.0).
 * Primary Font: Chivo
 * Code/Mono Font: JetBrains Mono
 * Sourced from kartikarora-compose-theme/references/Typography.kt.
 */
val ChivoFontFamily: FontFamily = FontFamily(
    Font(R.font.chivo_regular, FontWeight.Normal),
    Font(R.font.chivo_medium, FontWeight.Medium),
    Font(R.font.chivo_semibold, FontWeight.SemiBold),
    Font(R.font.chivo_bold, FontWeight.Bold),
    Font(R.font.chivo_black, FontWeight.Black),
)

val AlbertSansFontFamily: FontFamily = ChivoFontFamily

val JetBrainsMonoFontFamily: FontFamily = FontFamily(
    Font(R.font.jetbrains_mono_regular, FontWeight.Normal),
    Font(R.font.jetbrains_mono_medium, FontWeight.Medium),
    Font(R.font.jetbrains_mono_semibold, FontWeight.SemiBold),
    Font(R.font.jetbrains_mono_bold, FontWeight.Bold),
)

fun createKartikAroraTypography(
    sansFontFamily: FontFamily = ChivoFontFamily,
    monoFontFamily: FontFamily = JetBrainsMonoFontFamily
): Typography = Typography(
    // Display styles (Hero titles, large banner text)
    displayLarge = TextStyle(
        fontFamily = sansFontFamily,
        fontWeight = FontWeight.Black,
        fontSize = 48.sp,
        lineHeight = 56.sp,
        letterSpacing = (-0.03).em
    ),
    displayMedium = TextStyle(
        fontFamily = sansFontFamily,
        fontWeight = FontWeight.Black,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = (-0.03).em
    ),
    displaySmall = TextStyle(
        fontFamily = sansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 38.sp,
        letterSpacing = (-0.03).em
    ),

    // Heading styles (h1, h2, h3)
    headlineLarge = TextStyle(
        fontFamily = sansFontFamily,
        fontWeight = FontWeight.Black,
        fontSize = 28.sp,
        lineHeight = 34.sp,
        letterSpacing = (-0.03).em
    ),
    headlineMedium = TextStyle(
        fontFamily = sansFontFamily,
        fontWeight = FontWeight.Black,
        fontSize = 24.sp,
        lineHeight = 30.sp,
        letterSpacing = (-0.03).em
    ),
    headlineSmall = TextStyle(
        fontFamily = sansFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 26.sp
    ),

    // Titles (Cards, Dialogs, List Headers)
    titleLarge = TextStyle(
        fontFamily = sansFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    titleMedium = TextStyle(
        fontFamily = sansFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 22.sp
    ),
    titleSmall = TextStyle(
        fontFamily = sansFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),

    // Body Text
    bodyLarge = TextStyle(
        fontFamily = sansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = sansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    bodySmall = TextStyle(
        fontFamily = sansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),

    // Labels & Buttons (Buttons, Badges, Tabs, Chips)
    labelLarge = TextStyle(
        fontFamily = sansFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    labelMedium = TextStyle(
        fontFamily = sansFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = sansFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 14.sp
    )
)

val KartikAroraTypography = createKartikAroraTypography()

/**
 * Extended Brand Text Styles
 */
object KartikAroraTextStyles {
    val Blockquote = TextStyle(
        fontFamily = ChivoFontFamily,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = (-0.02).em
    )

    val Code = TextStyle(
        fontFamily = JetBrainsMonoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 18.sp
    )
}
