package com.ajrr.mytoptenmovies.presentation.login.movies

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ajrr.mytoptenmovies.R
import com.bumptech.glide.Glide

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val title = intent.getStringExtra("title")
        val posterPath = intent.getStringExtra("poster_path")
        val rating = intent.getFloatExtra("rating", 0f)
        val overview = intent.getStringExtra("overview")
        val releaseDate = intent.getStringExtra("release_date")

        val titleText = findViewById<TextView>(R.id.titleTextView)
        val posterImage = findViewById<ImageView>(R.id.posterImageView)
        val ratingText = findViewById<TextView>(R.id.ratingTextView)
        val overviewText = findViewById<TextView>(R.id.overviewTextView)
        val releaseDateText = findViewById<TextView>(R.id.releaseDateTextView)
        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()  // Cierra esta pantalla y vuelve a la anterior
        }

        titleText.text = title
        ratingText.text = "⭐ $rating"
        overviewText.text = overview
        releaseDateText.text = "📅 $releaseDate"

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500$posterPath")
            .into(posterImage)
    }
}
