package com.ajrr.mytoptenmovies.data.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val vote_average: Float,
    val poster_path: String,
    val release_date: String
)
