package me.kartikarora.icanhazstream.data

import kotlinx.coroutines.flow.Flow
import me.kartikarora.icanhazstream.model.Movie

interface MovieRepository {
    fun getTrendingMovies(): Flow<List<Movie>>
    suspend fun getMovieDetails(id: String): Movie?
    suspend fun searchMovies(query: String): List<Movie>
}
