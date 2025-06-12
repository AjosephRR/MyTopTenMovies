package com.ajrr.mytoptenmovies.presentation.login.movies


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajrr.mytoptenmovies.R
import com.ajrr.mytoptenmovies.data.datastore.remote.RetrofitInstance
import com.ajrr.mytoptenmovies.data.datastore.repository.MovieRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import androidx.recyclerview.widget.RecyclerView

class MovieListActivity : AppCompatActivity() {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewMovies)
        movieAdapter = MovieAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = movieAdapter

        val repository = MovieRepository("eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5NTAwYmRlNWRkNTM" +
                "wZmE0NGVjNDJiMjJiYjFiMDhmYSIsIm5iZiI6MTc0OTY5NjMyMC4xOTcsInN1YiI6IjY4NGEzZjQwNTYwMT" +
                "FmNWFjYzVjNmQ1YyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.juBoSpOht0NL5X3Ry6" +
                "jDPRdx002aG2fP1qvcJoaDbwA") // Sustituye aquí tu API Key
        val factory = MovieViewModelFactory(repository)
        movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]


        lifecycleScope.launch {
            movieViewModel.movies.collect { movies ->
                movieAdapter.submitList(movies)
            }
        }

        movieViewModel.loadTopRatedMovies()
    }
}
