package com.ajrr.mytoptenmovies.data.datastore.repository

import android.util.Log
import com.ajrr.mytoptenmovies.data.datastore.remote.RetrofitInstance
import com.ajrr.mytoptenmovies.data.model.Movie


class MovieRepository(private val apiKey: String) {

    private val apiService = RetrofitInstance.api

    suspend fun getTopRatedMovies(): List<Movie> {
        return try {
            val response = apiService.getTopRatedMovies(apiKey)
            response.results  // <-- asegúrate que tu MovieResponse tenga esta propiedad
        } catch (e: Exception) {
            Log.e("MovieRepository", "Error al obtener películas", e)
            emptyList()
        }
    }
}

