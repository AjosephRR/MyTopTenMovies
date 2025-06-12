package com.ajrr.mytoptenmovies.presentation.login.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajrr.mytoptenmovies.data.datastore.repository.MovieRepository
import com.ajrr.mytoptenmovies.data.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    fun fetchTopRatedMovies() {
        viewModelScope.launch {
            try {
                val topMovies = repository.getTopRatedMovies()
                _movies.value = topMovies.take(10)
            } catch (e: Exception) {
                // Aquí puedes manejar errores (log, mostrar error, etc.)
                e.printStackTrace()
            }
        }
    }
}