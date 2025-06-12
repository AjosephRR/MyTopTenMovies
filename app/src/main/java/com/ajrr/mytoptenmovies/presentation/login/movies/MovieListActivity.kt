package com.ajrr.mytoptenmovies.presentation.login.movies



import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ajrr.mytoptenmovies.R
import com.ajrr.mytoptenmovies.data.datastore.UserPreferences
import com.ajrr.mytoptenmovies.data.datastore.repository.MovieRepository
import com.ajrr.mytoptenmovies.presentation.login.LoginActivity
import kotlinx.coroutines.launch

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

        val repository = MovieRepository("9500bde5dd530fa44ec42b22bb1b08fa")
        val factory = MovieViewModelFactory(repository)
        movieViewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]


        lifecycleScope.launch {
            movieViewModel.movies.collect { movies ->
                movieAdapter.submitList(movies)
            }
        }

        movieViewModel.loadTopRatedMovies()

        val btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            // ⚠️ Limpiar el login guardado si estás usando DataStore
            lifecycleScope.launch {
                val preferences = UserPreferences(this@MovieListActivity)
                preferences.clearSession()  // <- asegúrate que esto exista
                startActivity(Intent(this@MovieListActivity, LoginActivity::class.java))
                finish() // Cierra esta pantalla para que no se pueda volver atrás
            }
        }
    }
}
