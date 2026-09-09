package me.kartikarora.icanhazstream.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.kartikarora.icanhazstream.model.Movie
import me.kartikarora.icanhazstream.model.StreamingProvider
import me.kartikarora.icanhazstream.model.WatchOptionType

/**
 * Sample movie dataset with Australian streaming providers.
 */
object SampleMovieData {

    val sampleMovies = listOf(
        Movie(
            id = "m1",
            title = "Dune: Part Two",
            overview = "Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family.",
            posterPath = "https://image.tmdb.org/t/p/w500/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg",
            releaseDate = "2024-03-01",
            voteAverage = 8.6,
            providers = listOf(
                StreamingProvider("netflix", "Netflix", null, WatchOptionType.STREAM, 16.99, "4K"),
                StreamingProvider("binge", "Binge", null, WatchOptionType.STREAM, 10.00, "4K"),
                StreamingProvider("apple_tv", "Apple TV", null, WatchOptionType.RENT, 6.99, "4K"),
                StreamingProvider("prime", "Prime Video", null, WatchOptionType.BUY, 24.99, "4K"),
            ),
        ),
        Movie(
            id = "m2",
            title = "Spider-Man: Across the Spider-Verse",
            overview = "Miles Morales catapults across the Multiverse, where he encounters a team of Spider-People charged with protecting its very existence.",
            posterPath = "https://image.tmdb.org/t/p/w500/8Vt6mWEReuy4Of61Lnj5Xj704m8.jpg",
            releaseDate = "2023-06-02",
            voteAverage = 8.7,
            providers = listOf(
                StreamingProvider("netflix", "Netflix", null, WatchOptionType.STREAM, 16.99, "4K"),
                StreamingProvider("stan", "Stan", null, WatchOptionType.STREAM, 12.00, "HD"),
                StreamingProvider("apple_tv", "Apple TV", null, WatchOptionType.RENT, 4.99, "4K"),
            ),
        ),
        Movie(
            id = "m3",
            title = "Oppenheimer",
            overview = "The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb.",
            posterPath = "https://image.tmdb.org/t/p/w500/8Gxv8gSFCU0XGDykEGv7zR1n2ua.jpg",
            releaseDate = "2023-07-21",
            voteAverage = 8.9,
            providers = listOf(
                StreamingProvider("binge", "Binge", null, WatchOptionType.STREAM, 10.00, "4K"),
                StreamingProvider("netflix", "Netflix", null, WatchOptionType.STREAM, 16.99, "4K"),
                StreamingProvider("apple_tv", "Apple TV", null, WatchOptionType.BUY, 19.99, "4K"),
            ),
        ),
        Movie(
            id = "m4",
            title = "Inside Out 2",
            overview = "Teenager Riley's mind headquarters undergoes a sudden demolition to make room for unexpected new Emotions.",
            posterPath = "https://image.tmdb.org/t/p/w500/vpnVM9B6NMmQpWeZvzLvDESb2QY.jpg",
            releaseDate = "2024-06-14",
            voteAverage = 7.7,
            providers = listOf(
                StreamingProvider("disney", "Disney+", null, WatchOptionType.STREAM, 13.99, "4K"),
                StreamingProvider("apple_tv", "Apple TV", null, WatchOptionType.RENT, 5.99, "4K"),
            ),
        ),
        Movie(
            id = "m5",
            title = "The Fall Guy",
            overview = "A battered stuntman springs back into action when the star of a mega-budget movie disappears.",
            posterPath = "https://image.tmdb.org/t/p/w500/aBkqu79NaI39q2vez57DaaBiq0V.jpg",
            releaseDate = "2024-05-03",
            voteAverage = 7.3,
            providers = listOf(
                StreamingProvider("binge", "Binge", null, WatchOptionType.STREAM, 10.00, "4K"),
                StreamingProvider("apple_tv", "Apple TV", null, WatchOptionType.RENT, 6.99, "4K"),
                StreamingProvider("prime", "Prime Video", null, WatchOptionType.RENT, 6.99, "4K"),
            ),
        ),
        Movie(
            id = "m6",
            title = "Furiosa: A Mad Max Saga",
            overview = "The origin story of renegade warrior Furiosa before her encounter and teamup with Mad Max in Fury Road.",
            posterPath = "https://image.tmdb.org/t/p/w500/iADOJ8Zymht2JPMoy3R7xUMZ51f.jpg",
            releaseDate = "2024-05-24",
            voteAverage = 7.8,
            providers = listOf(
                StreamingProvider("netflix", "Netflix", null, WatchOptionType.STREAM, 16.99, "4K"),
                StreamingProvider("stan", "Stan", null, WatchOptionType.STREAM, 12.00, "4K"),
                StreamingProvider("apple_tv", "Apple TV", null, WatchOptionType.RENT, 6.99, "4K"),
            ),
        ),
    )
}

/**
 * Default in-memory implementation of [MovieRepository] backed by sample data.
 */
class DefaultMovieRepository : MovieRepository {

    private val moviesFlow = MutableStateFlow(SampleMovieData.sampleMovies)

    override fun getTrendingMovies(): Flow<List<Movie>> = moviesFlow.asStateFlow()

    override suspend fun getMovieDetails(id: String): Movie? {
        return moviesFlow.value.find { it.id == id }
    }

    override suspend fun searchMovies(query: String): List<Movie> {
        return moviesFlow.value.filter {
            it.title.contains(query, ignoreCase = true) || it.overview.contains(query, ignoreCase = true)
        }
    }
}
