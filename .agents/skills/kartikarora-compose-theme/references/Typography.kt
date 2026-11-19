package me.kartikarora.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

/**
 * @kartikarora Brand Typography definition.
 * Primary Font: Albert Sans
 * Code/Mono Font: JetBrains Mono
 *
 * =========================================================================
 * 📱 ANDROID GOOGLE FONTS SETUP (Downloadable Fonts)
 * =========================================================================
 * 1. Add Gradle dependency in app/build.gradle.kts:
 *    implementation("androidx.compose.ui:ui-text-google-fonts:<compose_version>")
 *
 * 2. Ensure `res/values/font_certs.xml` contains Google Play Services certs.
 *
 * 3. Define GoogleFont FontFamily:
 *    ```kotlin
 *    import androidx.compose.ui.text.googlefonts.GoogleFont
 *    import androidx.compose.ui.text.googlefonts.Font
 *    import <your.package>.R
 *
 *    val googleFontProvider = GoogleFont.Provider(
 *        providerAuthority = "com.google.android.gms.fonts",
 *        providerPackage = "com.google.android.gms",
 *        certificates = R.array.com_google_android_gms_fonts_certs
 *    )
 *
 *    val albertSansGoogleFont = GoogleFont("Albert Sans")
 *    val jetBrainsMonoGoogleFont = GoogleFont("JetBrains Mono")
 *
 *    val AlbertSansFontFamily = FontFamily(
 *        Font(googleFont = albertSansGoogleFont, fontProvider = googleFontProvider, weight = FontWeight.Light),
 *        Font(googleFont = albertSansGoogleFont, fontProvider = googleFontProvider, weight = FontWeight.Normal),
 *        Font(googleFont = albertSansGoogleFont, fontProvider = googleFontProvider, weight = FontWeight.Medium),
 *        Font(googleFont = albertSansGoogleFont, fontProvider = googleFontProvider, weight = FontWeight.SemiBold),
 *        Font(googleFont = albertSansGoogleFont, fontProvider = googleFontProvider, weight = FontWeight.Bold)
 *    )
 *
 *    val JetBrainsMonoFontFamily = FontFamily(
 *        Font(googleFont = jetBrainsMonoGoogleFont, fontProvider = googleFontProvider, weight = FontWeight.Normal),
 *        Font(googleFont = jetBrainsMonoGoogleFont, fontProvider = googleFontProvider, weight = FontWeight.Medium),
 *        Font(googleFont = jetBrainsMonoGoogleFont, fontProvider = googleFontProvider, weight = FontWeight.SemiBold),
 *        Font(googleFont = jetBrainsMonoGoogleFont, fontProvider = googleFontProvider, weight = FontWeight.Bold)
 *    )
 *    ```
 * =========================================================================
 */

// Fallback / default font families if not using Google Fonts provider directly
val AlbertSansFontFamily: FontFamily = FontFamily.SansSerif
val JetBrainsMonoFontFamily: FontFamily = FontFamily.Monospace

/**
 * Creates brand typography with custom or Google Fonts font families.
 */
fun createKartikAroraTypography(
    sansFontFamily: FontFamily = AlbertSansFontFamily,
    monoFontFamily: FontFamily = JetBrainsMonoFontFamily
): Typography = Typography(
    // Display styles (Hero titles, large banner text)
    displayLarge = TextStyle(
        fontFamily = sansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp,
        lineHeight = 56.sp,
        letterSpacing = (-0.02).em
    ),
    displayMedium = TextStyle(
        fontFamily = sansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = (-0.02).em
    ),
    displaySmall = TextStyle(
        fontFamily = sansFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 30.sp,
        lineHeight = 38.sp,
        letterSpacing = (-0.02).em
    ),

    // Heading styles (h1, h2, h3)
    headlineLarge = TextStyle(
        fontFamily = sansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 34.sp,
        letterSpacing = (-0.02).em
    ),
    headlineMedium = TextStyle(
        fontFamily = sansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 30.sp,
        letterSpacing = (-0.01).em
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
    // High-impact loud pull quote
    val Blockquote = TextStyle(
        fontFamily = AlbertSansFontFamily,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = (-0.02).em
    )

    // Code & Monospace block
    val Code = TextStyle(
        fontFamily = JetBrainsMonoFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 18.sp
    )
}
