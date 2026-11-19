package me.kartikarora.icanhazstream.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import me.kartikarora.icanhazstream.data.WatchCostUtils
import me.kartikarora.icanhazstream.model.Movie
import me.kartikarora.icanhazstream.model.StreamingProvider
import me.kartikarora.icanhazstream.model.WatchOptionType
import me.kartikarora.icanhazstream.ui.components.ProviderBadge
import me.kartikarora.icanhazstream.ui.components.RatingChip
import me.kartikarora.icanhazstream.ui.theme.AppleTvDark
import me.kartikarora.icanhazstream.ui.theme.BingeOrange
import me.kartikarora.icanhazstream.ui.theme.DisneyBlue
import me.kartikarora.icanhazstream.ui.theme.ICanHazStreamTheme
import me.kartikarora.icanhazstream.ui.theme.NetflixRed
import me.kartikarora.icanhazstream.ui.theme.PrimeVideoBlue
import me.kartikarora.icanhazstream.ui.components.BrandButton
import me.kartikarora.icanhazstream.ui.components.BrandButtonVariant
import me.kartikarora.icanhazstream.ui.components.BrandStatement
import me.kartikarora.icanhazstream.ui.components.SlidingTabs
import me.kartikarora.icanhazstream.ui.components.StatusBadge
import me.kartikarora.icanhazstream.ui.components.StatusBadgeVariant
import me.kartikarora.icanhazstream.ui.components.TokyoCard
import me.kartikarora.icanhazstream.ui.theme.StanGreen
import me.kartikarora.icanhazstream.ui.theme.spacing

/**
 * Where To Watch screen — displays streaming, rental, and purchase availability in Australia.
 */
@Composable
fun WhereToWatchScreen(
    movie: Movie?,
    onProviderClick: (StreamingProvider) -> Unit,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
) {
    if (movie == null) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "Movie not found", style = MaterialTheme.typography.bodyLarge)
        }
        return
    }

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Stream", "Rent", "Buy")

    val currentType = when (selectedTabIndex) {
        0 -> WatchOptionType.STREAM
        1 -> WatchOptionType.RENT
        else -> WatchOptionType.BUY
    }

    val filteredProviders = movie.providers.filter { it.type == currentType }

    val rentPrice = movie.providers.find { it.type == WatchOptionType.RENT }?.price ?: 0.0
    val buyPrice = movie.providers.find { it.type == WatchOptionType.BUY }?.price ?: 0.0
    val streamPrice = movie.providers.find { it.type == WatchOptionType.STREAM }?.price ?: 12.99
    val bestValueRecommendation = WatchCostUtils.calculateBestValueOption(
        rentPrice = rentPrice,
        buyPrice = buyPrice,
        monthlySubPrice = streamPrice,
        expectedRewatches = 1,
    )

    val spacing = MaterialTheme.spacing

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(spacing.small),
        verticalArrangement = Arrangement.spacedBy(spacing.small),
    ) {
        // Movie Header
        item {
            TokyoCard(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = movie.title,
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.weight(1f),
                        )
                        RatingChip(rating = movie.voteAverage)
                    }

                    movie.releaseDate?.let { date ->
                        Text(
                            text = "Release Date: $date",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = spacing.extraSmall),
                        )
                    }

                    Spacer(modifier = Modifier.height(spacing.extraSmall))

                    Text(
                        text = movie.overview,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }

        // Cost Analysis Recommendation Banner
        item {
            BrandStatement(
                quote = "Value Pick: $bestValueRecommendation",
                modifier = Modifier.fillMaxWidth(),
            )
        }

        // Stream / Rent / Buy Tabs
        item {
            SlidingTabs(
                tabs = tabs,
                selectedTab = tabs[selectedTabIndex],
                onTabSelected = { selectedTabIndex = tabs.indexOf(it) },
                tabLabel = { it },
            )
        }

        // Provider Options
        if (filteredProviders.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.spacing.large),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Not available to ${tabs[selectedTabIndex].lowercase()} in Australia right now",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        } else {
            items(filteredProviders) { provider ->
                val badgeColor = when (provider.id.lowercase()) {
                    "netflix" -> NetflixRed
                    "disney" -> DisneyBlue
                    "prime" -> PrimeVideoBlue
                    "apple_tv" -> AppleTvDark
                    "stan" -> StanGreen
                    "binge" -> BingeOrange
                    else -> MaterialTheme.colorScheme.primary
                }

                TokyoCard(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onProviderClick(provider) },
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            ProviderBadge(name = provider.name, badgeColor = badgeColor)
                            Spacer(modifier = Modifier.width(spacing.extraSmall))
                            Column(verticalArrangement = Arrangement.spacedBy(spacing.extraSmall)) {
                                provider.price?.let { price ->
                                    Text(
                                        text = "AU\$$price" + if (provider.type == WatchOptionType.STREAM) "/mo" else "",
                                        style = MaterialTheme.typography.labelLarge,
                                        fontWeight = FontWeight.Bold,
                                    )
                                }
                                provider.quality?.let { q ->
                                    StatusBadge(
                                        label = q,
                                        variant = StatusBadgeVariant.Secondary,
                                    )
                                }
                            }
                        }

                        BrandButton(
                            text = "Watch",
                            variant = BrandButtonVariant.Small,
                            onClick = { onProviderClick(provider) },
                        )
                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Preview(showBackground = true)
@Composable
fun WhereToWatchPreview() {
    ICanHazStreamTheme {
        WhereToWatchScreen(
            movie = Movie(
                id = "preview_1",
                title = "Dune: Part Two",
                overview = "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family.",
                releaseDate = "2024-03-01",
                voteAverage = 8.6,
                providers = listOf(
                    StreamingProvider("netflix", "Netflix", null, WatchOptionType.STREAM, 16.99, "4K"),
                    StreamingProvider("apple_tv", "Apple TV", null, WatchOptionType.RENT, 6.99, "4K"),
                    StreamingProvider("prime", "Prime Video", null, WatchOptionType.BUY, 24.99, "4K"),
                ),
            ),
            onProviderClick = {},
        )
    }
}
