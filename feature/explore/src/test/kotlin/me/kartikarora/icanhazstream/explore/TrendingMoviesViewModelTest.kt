package me.kartikarora.icanhazstream.explore

import me.kartikarora.icanhazstream.testing.FakeMovieRepository
import me.kartikarora.icanhazstream.testing.MainDispatcherExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * Unit tests for [TrendingMoviesViewModel] using [FakeMovieRepository].
 */
@ExtendWith(MainDispatcherExtension::class)
class TrendingMoviesViewModelTest {

    private lateinit var repository: FakeMovieRepository
    private lateinit var viewModel: TrendingMoviesViewModel

    @BeforeEach
    fun setup() {
        repository = FakeMovieRepository()
        viewModel = TrendingMoviesViewModel(repository)
    }

    @Test
    fun `initial state has empty movies list`() {
        assert(viewModel.trendingMovies.value?.isEmpty() == true)
    }
}
