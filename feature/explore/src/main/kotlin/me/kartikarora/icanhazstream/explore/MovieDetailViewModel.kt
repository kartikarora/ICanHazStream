package me.kartikarora.icanhazstream.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.kartikarora.icanhazstream.data.MovieRepository
import me.kartikarora.icanhazstream.model.Movie

/**
 * ViewModel for the Movie Detail screen.
 * Safely handles null values and empty regional provider data.
 */
class MovieDetailViewModel(
    private val movieId: String,
    private val repository: MovieRepository,
) : ViewModel() {

    private val _movie = MutableStateFlow<Movie?>(null)
    val movie: StateFlow<Movie?> = _movie.asStateFlow()

    private val _streamSummary = MutableStateFlow<String>("")
    val streamSummary: StateFlow<String> = _streamSummary.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadMovieDetails()
    }

    private fun loadMovieDetails() {
        _isLoading.value = true
        viewModelScope.launch {
            val movie = repository.getMovieDetails(movieId)
            _movie.value = movie

            // Safely compute stream summary
            _streamSummary.value = when {
                movie == null -> "Movie details unavailable"
                movie.providers.isEmpty() -> "${movie.title} is not currently streaming in Australia"
                else -> "${movie.title} is streaming on ${movie.providers.joinToString(", ") { it.name }}"
            }
            _isLoading.value = false
        }
    }
}
