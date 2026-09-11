package me.kartikarora.icanhazstream.watchlist

import app.cash.turbine.test
import kotlinx.coroutines.test.runTest
import me.kartikarora.icanhazstream.model.Movie
import me.kartikarora.icanhazstream.testing.MainDispatcherExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * Unit tests for [WatchlistViewModel] using Turbine.
 */
@ExtendWith(MainDispatcherExtension::class)
class WatchlistViewModelTest {

    private lateinit var repository: DefaultWatchlistRepository
    private lateinit var viewModel: WatchlistViewModel

    @BeforeEach
    fun setup() {
        repository = DefaultWatchlistRepository()
        viewModel = WatchlistViewModel(repository)
    }

    @Test
    fun `initial watchlist state loads pre-populated movies`() = runTest {
        viewModel.watchlist.test {
            val initial = awaitItem()
            assert(initial.isNotEmpty())
        }
    }

    @Test
    fun `adding movie to watchlist updates flow`() = runTest {
        val newMovie = Movie(id = "test_movie", title = "Test Movie", overview = "Overview")

        viewModel.watchlist.test {
            awaitItem() // initial state

            viewModel.toggleWatchlist(newMovie)

            val updated = awaitItem()
            assert(updated.any { it.id == "test_movie" })
        }
    }

    @Test
    fun `removing movie from watchlist updates flow`() = runTest {
        val movieToRemove = viewModel.watchlist.value.first()

        viewModel.watchlist.test {
            awaitItem() // initial state

            viewModel.removeFromWatchlist(movieToRemove.id)

            val updated = awaitItem()
            assert(updated.none { it.id == movieToRemove.id })
        }
    }
}
