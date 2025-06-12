package com.ajrr.mytoptenmovies.data.datastore.repository

import android.util.Log
import com.ajrr.mytoptenmovies.data.datastore.remote.TmdbApiService
import com.ajrr.mytoptenmovies.data.model.Movie
import javax.inject.Inject
import javax.inject.Named

class MovieRepository @Inject constructor(
    @Named("tmdb_api_key") private val apiKey: String,
    private val apiService: TmdbApiService
) {

    suspend fun getTopRatedMovies(): List<Movie> {
        return try {
            val response = apiService.getTopRatedMovies(apiKey)
            response.results
        } catch (e: Exception) {
            Log.e("MovieRepository", "Error al obtener películas", e)
            emptyList()
        }
    }
    suspend fun getMovieDetails(movieId: Int): Movie {
        return apiService.getMovieDetails(movieId, apiKey)
    }

}

