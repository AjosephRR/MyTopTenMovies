package com.ajrr.mytoptenmovies.presentation.login.movies


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajrr.mytoptenmovies.data.datastore.repository.MovieRepository
import com.ajrr.mytoptenmovies.data.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    fun loadTopRatedMovies() {
        viewModelScope.launch {
            _movies.value = repository.getTopRatedMovies().take(10)
        }
    }
}

