package com.ajrr.mytoptenmovies.data.datastore.repository

import com.ajrr.mytoptenmovies.data.datastore.remote.RetrofitInstance
import com.ajrr.mytoptenmovies.data.model.Movie


class MovieRepository(private val apiKey: String) {

    suspend fun getTopRatedMovies(): List<Movie> {
        return RetrofitInstance.api.getTopRatedMovies(apiKey).results
    }
}
