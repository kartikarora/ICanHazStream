package me.kartikarora.icanhazstream.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.kartikarora.icanhazstream.data.MovieRepository
import me.kartikarora.icanhazstream.model.Movie

class MovieDetailViewModel(
    private val repository: MovieRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieDetailUiState>(MovieDetailUiState.Loading)
    val uiState: StateFlow<MovieDetailUiState> = _uiState.asStateFlow()

    fun loadMovieDetails(movieId: String, countryCode: String = "AU") {
        viewModelScope.launch {
            _uiState.value = MovieDetailUiState.Loading
            try {
                val movie = repository.getMovieDetails(movieId)
                if (movie != null) {
                    val providers = movie.providers.filter { it.type.name == "STREAM" }
                    // TODO: Step 13 — Fix NullPointerException crash when title has no providers in selected country
                    val primaryProvider = providers.first().name // Causes NoSuchElementException / Crash
                    _uiState.value = MovieDetailUiState.Success(movie, primaryProvider)
                } else {
                    _uiState.value = MovieDetailUiState.Error("Movie not found")
                }
            } catch (e: Exception) {
                _uiState.value = MovieDetailUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed interface MovieDetailUiState {
    data object Loading : MovieDetailUiState
    data class Success(val movie: Movie, val primaryProvider: String) : MovieDetailUiState
    data class Error(val message: String) : MovieDetailUiState
}
