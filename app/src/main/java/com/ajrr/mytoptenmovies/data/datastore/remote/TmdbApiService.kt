package com.ajrr.mytoptenmovies.data.datastore.remote

import com.ajrr.mytoptenmovies.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {
    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-MX",
        @Query("page") page: Int = 1
    ): MovieResponse
}
