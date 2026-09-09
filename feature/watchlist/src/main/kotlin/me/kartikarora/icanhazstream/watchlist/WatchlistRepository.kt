package me.kartikarora.icanhazstream.watchlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.kartikarora.icanhazstream.model.Movie

// TODO: Step 10 — Scaffold WatchlistRepository with Agent Mode
class WatchlistRepository {
    private val _watchlist = MutableStateFlow<List<Movie>>(emptyList())
    val watchlist: Flow<List<Movie>> = _watchlist.asStateFlow()

    fun addToWatchlist(movie: Movie) {
        if (_watchlist.value.none { it.id == movie.id }) {
            _watchlist.value = _watchlist.value + movie
        }
    }

    fun removeFromWatchlist(movieId: String) {
        _watchlist.value = _watchlist.value.filterNot { it.id == movieId }
    }
}
