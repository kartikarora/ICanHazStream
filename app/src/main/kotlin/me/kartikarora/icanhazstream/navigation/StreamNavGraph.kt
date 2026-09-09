package me.kartikarora.icanhazstream.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import me.kartikarora.icanhazstream.ui.components.StreamTopBar

/**
 * Top-level navigation graph for ICanHazStream.
 * Connects Explore, Movie Detail, and Watchlist screens.
 */
@Composable
fun StreamNavGraph(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            StreamTopBar(title = "ICanHazStream")
        },
        modifier = modifier,
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center,
        ) {
            Text("ICanHazStream Navigation — TODO Step 17")
        }
    }
}
