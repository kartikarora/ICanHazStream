package me.kartikarora.icanhazstream.explore

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import me.kartikarora.icanhazstream.model.Movie
import me.kartikarora.icanhazstream.ui.components.MovieCard
import me.kartikarora.icanhazstream.ui.components.ProviderBadge
import me.kartikarora.icanhazstream.ui.components.RatingChip
import me.kartikarora.icanhazstream.ui.theme.AppleTvDark
import me.kartikarora.icanhazstream.ui.theme.BingeOrange
import me.kartikarora.icanhazstream.ui.theme.DisneyBlue
import me.kartikarora.icanhazstream.ui.theme.NetflixRed
import me.kartikarora.icanhazstream.ui.theme.PrimeVideoBlue
import me.kartikarora.icanhazstream.ui.theme.StanGreen

/**
 * Composable card displaying a movie and its available regional streaming providers.
 * Uses brand components from :core:ui.
 */
@Composable
fun MovieProviderCard(
    movie: Movie,
    onClick: (Movie) -> Unit,
    modifier: Modifier = Modifier,
) {
    MovieCard(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        onClick = { onClick(movie) },
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f),
                )
                RatingChip(rating = movie.voteAverage)
            }

            movie.releaseDate?.let { date ->
                Text(
                    text = "Released: $date",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    modifier = Modifier.padding(top = 2.dp),
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = movie.overview,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
            )

            if (movie.providers.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Available On:",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                )
                Spacer(modifier = Modifier.height(4.dp))
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    items(movie.providers) { provider ->
                        val color = when (provider.id.lowercase()) {
                            "netflix" -> NetflixRed
                            "disney" -> DisneyBlue
                            "prime" -> PrimeVideoBlue
                            "apple_tv" -> AppleTvDark
                            "stan" -> StanGreen
                            "binge" -> BingeOrange
                            else -> MaterialTheme.colorScheme.primary
                        }
                        ProviderBadge(name = provider.name, badgeColor = color)
                    }
                }
            }
        }
    }
}
