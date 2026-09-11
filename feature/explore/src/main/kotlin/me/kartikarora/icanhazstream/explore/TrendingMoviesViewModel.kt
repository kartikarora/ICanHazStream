package me.kartikarora.icanhazstream.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.kartikarora.icanhazstream.data.MovieRepository
import me.kartikarora.icanhazstream.model.Movie

/**
 * ViewModel for the Trending Movies / Explore screen.
 * Uses StateFlow for modern reactive Compose UI state management.
 */
class TrendingMoviesViewModel(
    private val repository: MovieRepository,
) : ViewModel() {

    private val _trendingMovies = MutableStateFlow<List<Movie>>(emptyList())
    val trendingMovies: StateFlow<List<Movie>> = _trendingMovies.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _selectedCategory = MutableStateFlow("All")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    init {
        loadTrendingMovies()
    }

    fun selectCategory(category: String) {
        _selectedCategory.value = category
    }

    fun loadTrendingMovies() {
        _isLoading.value = true
        _errorMessage.value = null
        viewModelScope.launch {
            try {
                repository.getTrendingMovies().collectLatest { movies ->
                    _trendingMovies.value = movies
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Failed to load trending movies"
                _isLoading.value = false
            }
        }
    }
}
