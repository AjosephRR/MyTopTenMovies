package com.ajrr.mytoptenmovies.presentation.login.movies

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ajrr.mytoptenmovies.R
import com.ajrr.mytoptenmovies.data.datastore.repository.MovieRepository
import com.ajrr.mytoptenmovies.data.model.Movie
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    @Inject lateinit var repository: MovieRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieId = intent.getIntExtra("movie_id", -1)

        val titleText = findViewById<TextView>(R.id.titleTextView)
        val posterImage = findViewById<ImageView>(R.id.posterImageView)
        val ratingText = findViewById<TextView>(R.id.ratingTextView)
        val overviewText = findViewById<TextView>(R.id.overviewTextView)
        val releaseDateText = findViewById<TextView>(R.id.releaseDateTextView)
        val btnBack = findViewById<Button>(R.id.btnBack)

        btnBack.setOnClickListener {
            finish()
        }

        if (movieId != -1) {
            lifecycleScope.launch {
                try {
                    val movie: Movie = repository.getMovieDetails(movieId)

                    titleText.text = movie.title
                    ratingText.text = "⭐ ${movie.vote_average}"
                    overviewText.text = movie.overview
                    releaseDateText.text = "📅 ${movie.release_date}"

                    Glide.with(this@MovieDetailActivity)
                        .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
                        .into(posterImage)
                } catch (e: Exception) {
                    Toast.makeText(this@MovieDetailActivity, "Error al obtener detalles", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this, "ID de película inválido", Toast.LENGTH_SHORT).show()
        }
    }
}
