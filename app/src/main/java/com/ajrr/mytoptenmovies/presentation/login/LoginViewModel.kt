package com.ajrr.mytoptenmovies.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ajrr.mytoptenmovies.data.datastore.UserPreferences
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LoginViewModel(private val userPreferences: UserPreferences) : ViewModel() {

    val isLoggedIn = userPreferences.isLoggedIn
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    fun login(email: String, password: String) {
        if (email.isNotBlank() && password.length >= 4) {
            viewModelScope.launch {
                userPreferences.setLoggedIn(true)
            }
        }
    }

    class Factory(private val userPreferences: UserPreferences) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(userPreferences) as T
        }
    }
}
