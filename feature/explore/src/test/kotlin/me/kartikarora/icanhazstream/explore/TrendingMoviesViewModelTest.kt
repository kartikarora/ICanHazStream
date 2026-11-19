package me.kartikarora.icanhazstream.explore

import me.kartikarora.icanhazstream.testing.FakeMovieRepository
import me.kartikarora.icanhazstream.testing.MainDispatcherRule
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Unit tests for [TrendingMoviesViewModel] using [FakeMovieRepository].
 */
class TrendingMoviesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: FakeMovieRepository
    private lateinit var viewModel: TrendingMoviesViewModel

    @Before
    fun setup() {
        repository = FakeMovieRepository()
        viewModel = TrendingMoviesViewModel(repository)
    }

    @Test
    fun initialStateHasEmptyMoviesList() {
        assertTrue(viewModel.trendingMovies.value.isEmpty())
    }
}
