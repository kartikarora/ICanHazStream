package me.kartikarora.icanhazstream.watchlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.kartikarora.icanhazstream.model.Movie
import me.kartikarora.icanhazstream.ui.components.BrandButton
import me.kartikarora.icanhazstream.ui.components.BrandButtonVariant
import me.kartikarora.icanhazstream.ui.components.TokyoCard
import me.kartikarora.icanhazstream.ui.theme.ICanHazStreamTheme
import me.kartikarora.icanhazstream.ui.theme.JetBrainsMonoFontFamily
import me.kartikarora.icanhazstream.ui.theme.spacing

/**
 * Watchlist Screen displaying movies saved by the user.
 */
@Composable
fun WatchlistScreen(
    viewModel: WatchlistViewModel,
    onMovieClick: (Movie) -> Unit,
    modifier: Modifier = Modifier,
) {
    val watchlist by viewModel.watchlist.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val spacing = MaterialTheme.spacing

    if (isLoading) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (watchlist.isEmpty()) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Your Watchlist is empty",
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.height(spacing.extraSmall))
                Text(
                    text = "Explore trending movies to add them to your list.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(spacing.small),
            verticalArrangement = Arrangement.spacedBy(spacing.extraSmall),
        ) {
            items(watchlist, key = { it.id }) { movie ->
                TokyoCard(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onMovieClick(movie) },
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = movie.title,
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            )
                            movie.releaseDate?.let { date ->
                                Text(
                                    text = date,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                            }
                            Text(
                                text = "★ ${movie.voteAverage} • ${movie.providers.size} services",
                                style = MaterialTheme.typography.labelSmall.copy(fontFamily = JetBrainsMonoFontFamily),
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(top = spacing.extraSmall),
                            )
                        }

                        BrandButton(
                            text = "Remove",
                            variant = BrandButtonVariant.Small,
                            onClick = { viewModel.removeFromWatchlist(movie.id) },
                        )
                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun WatchlistEmptyPreview() {
    ICanHazStreamTheme {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Your Watchlist is empty",
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
                Text(
                    text = "Explore trending movies to add them to your list.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}
