package me.kartikarora.icanhazstream.watchlist

import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import me.kartikarora.icanhazstream.model.Movie
import me.kartikarora.icanhazstream.testing.MainDispatcherRule
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Unit tests for [WatchlistViewModel] using Turbine.
 */
class WatchlistViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: DefaultWatchlistRepository
    private lateinit var viewModel: WatchlistViewModel

    @Before
    fun setup() {
        repository = DefaultWatchlistRepository()
        viewModel = WatchlistViewModel(repository)
    }

    @Test
    fun initialWatchlistStateLoadsPrePopulatedMovies() = runTest {
        viewModel.watchlist.test {
            val initial = awaitItem()
            assertTrue(initial.isNotEmpty())
        }
    }

    @Test
    fun addingMovieToWatchlistUpdatesFlow() = runTest {
        val newMovie = Movie(id = "test_movie", title = "Test Movie", overview = "Overview")

        viewModel.watchlist.test {
            awaitItem() // initial state

            viewModel.toggleWatchlist(newMovie)

            val updated = awaitItem()
            assertTrue(updated.any { it.id == "test_movie" })
        }
    }

    @Test
    fun removingMovieFromWatchlistUpdatesFlow() = runTest {
        val movieToRemove = viewModel.watchlist.value.first()

        viewModel.watchlist.test {
            awaitItem() // initial state

            viewModel.removeFromWatchlist(movieToRemove.id)

            val updated = awaitItem()
            assertTrue(updated.none { it.id == movieToRemove.id })
        }
    }
}
