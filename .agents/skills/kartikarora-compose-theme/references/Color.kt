package me.kartikarora.theme

import androidx.compose.ui.graphics.Color

/**
 * Brand Color Palette for @kartikarora design system (v2.0.0).
 * AUTO-GENERATED from tokens/design-tokens.json. DO NOT EDIT DIRECTLY.
 * Run 'node scripts/generate-tokens.js' to re-generate.
 */
object KartikAroraColors {
    // --- Light Palette Tokens (Paper-Anchored Canvas) ---
    val LightPaper = Color(0xFFFFFFFF) // #ffffff - Base canvas background (Architectural Concrete/White)
    val LightPaperAlt = Color(0xFFF4F6FA) // #f4f6fa - Elevated cards, wells, panels
    val LightPaperSunken = Color(0xFFE6EBF3) // #e6ebf3 - Code blocks, inputs, sunken wells
    val LightCard = Color(0xFFFFFFFF) // #ffffff - Card surface
    val LightInk = Color(0xFF0A111C) // #0a111c - Primary high-contrast text and headings
    val LightInkMuted = Color(0xFF49586E) // #49586e - Secondary text, subtitles, metadata
    val LightInkFaint = Color(0xFF7F90A6) // #7f90a6 - Disabled, borders, subtle text
    val LightRule = Color(0xFFCDD8E6) // #cdd8e6 - Hairline borders and rules
    val LightRuleStrong = Color(0xFF0A111C) // #0a111c - Active borders and dividers
    val LightSignal = Color(0xFF0084C7) // #0084c7 - Akihabara Cyan (High-contrast light)
    val LightSignalHover = Color(0xFF009CEB) // #009ceb - Hover state for signal
    val LightSignalWash = Color(0x1F0084C7) // rgba(0, 132, 199, 0.12) - Translucent cyan wash
    val LightSignalInk = Color(0xFFFFFFFF) // #ffffff - High-contrast text on signal fill
    val LightSignalSecondary = Color(0xFFE6B800) // #e6b800 - Caution Yellow secondary pop (Light)
    val LightSignalSecondaryHover = Color(0xFFFFCC00) // #ffcc00 - Hover state for secondary pop
    val LightSignalSecondaryInk = Color(0xFF0A111C) // #0a111c - High-contrast text on caution yellow
    val LightFocus = Color(0xFF0084C7) // #0084c7 - Instant accessibility focus ring
    val LightShadow = Color(0xFF0A111C14) // rgba(10, 17, 28, 0.08) - Card elevation shadow
    val LightShadowHover = Color(0xFF0A111C24) // rgba(10, 17, 28, 0.14) - Card hover elevation shadow
    val LightGlow = Color(0x1F0084C7) // rgba(0, 132, 199, 0.12) - Akihabara cyan glow
    val LightSuccess = Color(0xFF2DA44E) // #2da44e - Success indicators and badges
    val LightDanger = Color(0xFFCF222E) // #cf222e - Danger/error alerts and badges
    val LightWarning = Color(0xFFE6B800) // #e6b800 - Caution yellow indicator

    // Legacy Aliases
    val LightPrimaryText = LightInk
    val LightSecondaryText = LightInkMuted
    val LightTertiaryText = LightInkFaint
    val LightBackground = LightPaper
    val LightBackgroundAlt = LightPaperAlt
    val LightBorder = LightRule
    val LightAccent = LightSignal

    // --- Dark Palette Tokens (Obsidian-Anchored Canvas) ---
    val DarkPaper = Color(0xFF070A10) // #070a10 - Deep Space Obsidian canvas
    val DarkPaperAlt = Color(0xFF0D121C) // #0d121c - Elevated panels in obsidian
    val DarkPaperSunken = Color(0xFF04060A) // #04060a - Sunken code blocks & terminals
    val DarkCard = Color(0xFF121824) // #121824 - Card surface in obsidian
    val DarkInk = Color(0xFFF0F6FC) // #f0f6fc - Pure crisp text
    val DarkInkMuted = Color(0xFF8A9BB4) // #8a9bb4 - Muted slate secondary text
    val DarkInkFaint = Color(0xFF4E5F7A) // #4e5f7a - Subtle / disabled in dark
    val DarkRule = Color(0xFF1A2436) // #1a2436 - Hairline borders in dark
    val DarkRuleStrong = Color(0xFF273752) // #273752 - Active borders in dark
    val DarkSignal = Color(0xFF00F0FF) // #00f0ff - Akihabara Laser Cyan
    val DarkSignalHover = Color(0xFF33F3FF) // #33f3ff - Hover state for signal
    val DarkSignalWash = Color(0x2400F0FF) // rgba(0, 240, 255, 0.14) - Translucent laser cyan wash
    val DarkSignalInk = Color(0xFF000000) // #000000 - High-contrast text on laser cyan
    val DarkSignalSecondary = Color(0xFFFFE600) // #ffe600 - Caution Yellow secondary pop (Dark)
    val DarkSignalSecondaryHover = Color(0xFFFFF34D) // #fff34d - Hover state for secondary pop
    val DarkSignalSecondaryInk = Color(0xFF000000) // #000000 - High-contrast text on caution yellow
    val DarkFocus = Color(0xFF00F0FF) // #00f0ff - Instant focus ring in dark
    val DarkShadow = Color(0xFF000000B3) // rgba(0, 0, 0, 0.70) - Shadow in dark
    val DarkShadowHover = Color(0xFF00F0FF38) // rgba(0, 240, 255, 0.22) - Hover shadow in dark
    val DarkGlow = Color(0x2900F0FF) // rgba(0, 240, 255, 0.16) - Laser cyan glow in dark
    val DarkSuccess = Color(0xFF3FB950) // #3fb950 - Success in dark
    val DarkDanger = Color(0xFFF85149) // #f85149 - Danger in dark
    val DarkWarning = Color(0xFFFFE600) // #ffe600 - Caution yellow indicator

    // Legacy Aliases
    val DarkPrimaryText = DarkInk
    val DarkSecondaryText = DarkInkMuted
    val DarkTertiaryText = DarkInkFaint
    val DarkBackground = DarkPaper
    val DarkBackgroundAlt = DarkPaperAlt
    val DarkBorder = DarkRule
    val DarkAccent = DarkSignal

    // Utility Accents
    val Success = LightSuccess
    val Danger = LightDanger
    val Warning = LightWarning
}
