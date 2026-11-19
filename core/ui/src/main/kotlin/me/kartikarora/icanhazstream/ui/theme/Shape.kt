package me.kartikarora.icanhazstream.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * @kartikarora Brand Shapes (v2.0.0)
 * Sourced from kartikarora-compose-theme/references/Shape.kt.
 */
val KartikAroraShapes = Shapes(
    extraSmall = RoundedCornerShape(6.dp), // --radius-sm (Badges, tags, inputs)
    small = RoundedCornerShape(6.dp),
    medium = RoundedCornerShape(12.dp),     // --radius-md (Standard cards, code blocks)
    large = RoundedCornerShape(16.dp),      // --radius-lg (Modal sheets, large cards)
    extraLarge = RoundedCornerShape(24.dp)
)

object KartikAroraCustomShapes {
    val Pill = CircleShape                  // Badges, Filter chips, Tabs
    val Card = RoundedCornerShape(16.dp)    // Cards, forms, panels
    val Button = CircleShape                // Primary & Secondary CTA (full-round pill)
}
