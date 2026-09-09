package me.kartikarora.icanhazstream.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import me.kartikarora.icanhazstream.model.Movie

/**
 * JetBrains Ktor Client 3.5.2 — HTTP client for TMDB / Watchmode Streaming API.
 */
class KtorTmdbApi {

    private val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
    }

    suspend fun fetchTrendingMovies(): List<Movie> {
        return client.get("https://api.themoviedb.org/3/trending/movie/week").body()
    }

    suspend fun fetchMovieDetails(id: String): Movie {
        return client.get("https://api.themoviedb.org/3/movie/$id").body()
    }
}
