package com.ajrr.mytoptenmovies.presentation.login.movies


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ajrr.mytoptenmovies.R
import com.ajrr.mytoptenmovies.data.model.Movie
import com.bumptech.glide.Glide

class MovieAdapter : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(MovieDiffCallback()) {

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val posterImage: ImageView = view.findViewById(R.id.posterImageView)
        private val titleText: TextView = view.findViewById(R.id.titleTextView)
        private val ratingText: TextView = view.findViewById(R.id.ratingTextView)

        fun bind(movie: Movie) {
            titleText.text = movie.title
            ratingText.text = "⭐ ${movie.vote_average}"

            Glide.with(posterImage.context)
                .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
                .placeholder(R.drawable.ic_launcher_background) // Puedes personalizar el drawable si quieres
                .into(posterImage)

            // Aquí falta solo agregar el listener para ir a la pantalla de detalle
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, MovieDetailActivity::class.java)
                intent.putExtra("title", movie.title)
                intent.putExtra("poster_path", movie.poster_path)
                intent.putExtra("rating", movie.vote_average)
                intent.putExtra("overview", movie.overview)
                intent.putExtra("release_date", movie.release_date)
                itemView.context.startActivity(intent)
            }
        }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}

