package me.kartikarora.icanhazstream.watchlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.kartikarora.icanhazstream.data.SampleMovieData
import me.kartikarora.icanhazstream.model.Movie

/**
 * Repository interface for user watchlists.
 */
interface WatchlistRepository {
    fun getWatchlist(): Flow<List<Movie>>
    suspend fun addToWatchlist(movie: Movie)
    suspend fun removeFromWatchlist(movieId: String)
    suspend fun isInWatchlist(movieId: String): Boolean
}

/**
 * Default in-memory implementation of [WatchlistRepository].
 */
class DefaultWatchlistRepository : WatchlistRepository {

    private val watchlistMap = mutableMapOf<String, Movie>().apply {
        // Pre-populate with a couple of movies for nice demo experience
        SampleMovieData.sampleMovies.take(2).forEach { put(it.id, it) }
    }
    private val watchlistFlow = MutableStateFlow(watchlistMap.values.toList())

    override fun getWatchlist(): Flow<List<Movie>> = watchlistFlow.asStateFlow()

    override suspend fun addToWatchlist(movie: Movie) {
        watchlistMap[movie.id] = movie
        watchlistFlow.value = watchlistMap.values.toList()
    }

    override suspend fun removeFromWatchlist(movieId: String) {
        watchlistMap.remove(movieId)
        watchlistFlow.value = watchlistMap.values.toList()
    }

    override suspend fun isInWatchlist(movieId: String): Boolean {
        return watchlistMap.containsKey(movieId)
    }
}
