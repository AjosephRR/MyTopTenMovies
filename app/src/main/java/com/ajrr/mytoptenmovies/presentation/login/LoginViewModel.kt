package com.ajrr.mytoptenmovies.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajrr.mytoptenmovies.data.datastore.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userPreferences: UserPreferences
) : ViewModel() {

    val isLoggedIn = userPreferences.isLoggedIn
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    fun login(email: String, password: String) {
        // Simulación: cualquier combinación válida
        if (email.isNotBlank() && password.length >= 4) {
            viewModelScope.launch {
                userPreferences.setLoggedIn(true)
            }
        }
    }
}