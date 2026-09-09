package me.kartikarora.icanhazstream.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.kartikarora.icanhazstream.model.Movie

// TODO: Step 10 — Implement WatchlistViewModel with StateFlow
class WatchlistViewModel(
    private val repository: WatchlistRepository = WatchlistRepository(),
) : ViewModel() {

    val watchlist: StateFlow<List<Movie>> = repository.watchlist.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList(),
    )

    fun removeFromWatchlist(movieId: String) {
        viewModelScope.launch {
            repository.removeFromWatchlist(movieId)
        }
    }
}
