package me.kartikarora.icanhazstream.watchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.kartikarora.icanhazstream.model.Movie

/**
 * ViewModel for the Watchlist screen.
 */
class WatchlistViewModel(
    private val repository: WatchlistRepository,
) : ViewModel() {

    private val _watchlist = MutableStateFlow<List<Movie>>(emptyList())
    val watchlist: StateFlow<List<Movie>> = _watchlist.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadWatchlist()
    }

    fun loadWatchlist() {
        _isLoading.value = true
        viewModelScope.launch {
            repository.getWatchlist().collectLatest { movies ->
                _watchlist.value = movies
                _isLoading.value = false
            }
        }
    }

    fun toggleWatchlist(movie: Movie) {
        viewModelScope.launch {
            if (repository.isInWatchlist(movie.id)) {
                repository.removeFromWatchlist(movie.id)
            } else {
                repository.addToWatchlist(movie)
            }
        }
    }

    fun removeFromWatchlist(movieId: String) {
        viewModelScope.launch {
            repository.removeFromWatchlist(movieId)
        }
    }
}
