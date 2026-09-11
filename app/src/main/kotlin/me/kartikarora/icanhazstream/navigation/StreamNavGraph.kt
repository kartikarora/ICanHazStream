package me.kartikarora.icanhazstream.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import me.kartikarora.icanhazstream.data.DefaultMovieRepository
import me.kartikarora.icanhazstream.detail.WhereToWatchScreen
import me.kartikarora.icanhazstream.explore.TrendingMoviesScreen
import me.kartikarora.icanhazstream.explore.TrendingMoviesViewModel
import me.kartikarora.icanhazstream.model.Movie
import me.kartikarora.icanhazstream.watchlist.DefaultWatchlistRepository
import me.kartikarora.icanhazstream.watchlist.WatchlistScreen
import me.kartikarora.icanhazstream.watchlist.WatchlistViewModel

private sealed interface Screen {
    data object Explore : Screen
    data class Detail(val movie: Movie) : Screen
    data object Watchlist : Screen
}

/**
 * Top-level navigation graph for ICanHazStream.
 * Connects Explore, Movie Detail, and Watchlist screens.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StreamNavGraph(
    modifier: Modifier = Modifier,
    repository: DefaultMovieRepository = remember { DefaultMovieRepository() },
) {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Explore) }
    var previousScreen by remember { mutableStateOf<Screen>(Screen.Explore) }

    val exploreViewModel: TrendingMoviesViewModel = viewModel {
        TrendingMoviesViewModel(repository)
    }
    val watchlistViewModel: WatchlistViewModel = viewModel {
        WatchlistViewModel(DefaultWatchlistRepository())
    }

    val title = when (val screen = currentScreen) {
        Screen.Explore -> "ICanHazStream"
        is Screen.Detail -> screen.movie.title
        Screen.Watchlist -> "Watchlist"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    if (currentScreen is Screen.Detail) {
                        IconButton(onClick = { currentScreen = previousScreen }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                            )
                        }
                    }
                },
            )
        },
        bottomBar = {
            if (currentScreen !is Screen.Detail) {
                NavigationBar {
                    NavigationBarItem(
                        selected = currentScreen is Screen.Explore,
                        onClick = { currentScreen = Screen.Explore },
                        icon = { Icon(Icons.Default.Explore, contentDescription = "Explore") },
                        label = { Text("Explore") },
                    )
                    NavigationBarItem(
                        selected = currentScreen is Screen.Watchlist,
                        onClick = { currentScreen = Screen.Watchlist },
                        icon = { Icon(Icons.Default.Bookmark, contentDescription = "Watchlist") },
                        label = { Text("Watchlist") },
                    )
                }
            }
        },
        modifier = modifier,
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            when (val screen = currentScreen) {
                Screen.Explore -> {
                    TrendingMoviesScreen(
                        viewModel = exploreViewModel,
                        onMovieClick = { movie ->
                            previousScreen = Screen.Explore
                            currentScreen = Screen.Detail(movie)
                        },
                    )
                }
                is Screen.Detail -> {
                    WhereToWatchScreen(
                        movie = screen.movie,
                        onProviderClick = { /* Provider action */ },
                        onBackClick = { currentScreen = previousScreen },
                    )
                }
                Screen.Watchlist -> {
                    WatchlistScreen(
                        viewModel = watchlistViewModel,
                        onMovieClick = { movie ->
                            previousScreen = Screen.Watchlist
                            currentScreen = Screen.Detail(movie)
                        },
                    )
                }
            }
        }
    }
}
