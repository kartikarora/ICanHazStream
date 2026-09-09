package me.kartikarora.icanhazstream.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import me.kartikarora.icanhazstream.data.MovieRepository
import me.kartikarora.icanhazstream.model.Movie

/**
 * ViewModel managing trending movies and regional filtering.
 * Currently uses legacy LiveData — refactor to StateFlow in Step 03.
 */
class TrendingMoviesViewModel(
    private val repository: MovieRepository,
) : ViewModel() {

    private val _trendingMovies = MutableLiveData<List<Movie>>(emptyList())
    val trendingMovies: LiveData<List<Movie>> = _trendingMovies

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _selectedCategory = MutableLiveData<String>("All")
    val selectedCategory: LiveData<String> = _selectedCategory

    init {
        loadTrendingMovies()
    }

    fun loadTrendingMovies() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _trendingMovies.value = repository.getTrendingMovies().first()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun selectCategory(category: String) {
        _selectedCategory.value = category
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val allMovies = repository.getTrendingMovies().first()
                _trendingMovies.value = when (category) {
                    "Streaming" -> allMovies.filter { it.providers.any { p -> p.type.name == "STREAM" } }
                    "Rent / Buy" -> allMovies.filter { it.providers.any { p -> p.type.name == "RENT" || p.type.name == "BUY" } }
                    "Top Rated" -> allMovies.filter { it.voteAverage >= 8.0 }
                    else -> allMovies
                }
            } finally {
                _isLoading.value = false
            }
        }
    }
}
