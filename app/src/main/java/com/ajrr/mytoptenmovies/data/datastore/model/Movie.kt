package com.ajrr.mytoptenmovies.data.model

data class Movie(
    val id: Int,
    val title: String,
    val poster_path: String,
    val vote_average: Float,
    val overview: String,
    val release_date: String
)

