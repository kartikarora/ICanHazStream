package me.kartikarora.icanhazstream.watchlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import me.kartikarora.icanhazstream.model.Movie

// TODO: Step 10 — Scaffold WatchlistScreen with Agent Mode
@Composable
fun WatchlistScreen(
    onMovieClick: (Movie) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Watchlist Screen — TODO Step 10")
    }
}
