package me.kartikarora.icanhazstream.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import me.kartikarora.icanhazstream.model.Movie
import me.kartikarora.icanhazstream.model.StreamingProvider

/**
 * Where To Watch screen — displays streaming, rental, and purchase availability in Australia.
 * Generated from wireframe sketch in Step 07.
 */
// TODO: Step 07 — Generate Compose screen from wireframe mockup
@Composable
fun WhereToWatchScreen(
    movie: Movie?,
    onProviderClick: (StreamingProvider) -> Unit,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Where To Watch Screen — TODO Step 07")
    }
}
