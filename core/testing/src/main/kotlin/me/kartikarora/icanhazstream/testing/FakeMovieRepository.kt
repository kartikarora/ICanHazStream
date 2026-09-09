package me.kartikarora.icanhazstream.testing

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.kartikarora.icanhazstream.data.MovieRepository
import me.kartikarora.icanhazstream.model.Movie

/**
 * In-memory test fake for [MovieRepository].
 * Follows the project's "Fakes over Mocks" testing philosophy.
 */
class FakeMovieRepository : MovieRepository {

    private val moviesFlow = MutableStateFlow<List<Movie>>(emptyList())
    private val moviesMap = mutableMapOf<String, Movie>()

    override fun getTrendingMovies(): Flow<List<Movie>> = moviesFlow.asStateFlow()

    override suspend fun getMovieDetails(id: String): Movie? = moviesMap[id]

    override suspend fun searchMovies(query: String): List<Movie> {
        return moviesMap.values.filter { it.title.contains(query, ignoreCase = true) }
    }

    /**
     * Test helper: Emit a list of movies into the trending flow.
     */
    fun emitMovies(movies: List<Movie>) {
        movies.forEach { moviesMap[it.id] = it }
        moviesFlow.value = movies
    }
}
