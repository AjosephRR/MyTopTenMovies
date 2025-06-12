package com.ajrr.mytoptenmovies.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ajrr.mytoptenmovies.R
import com.ajrr.mytoptenmovies.data.datastore.UserPreferences
import com.ajrr.mytoptenmovies.presentation.login.movies.MovieListActivity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Crear UserPreferences y ViewModel
        val userPreferences = UserPreferences(applicationContext)
        viewModel = ViewModelProvider(this, LoginViewModel.Factory(userPreferences))[LoginViewModel::class.java]

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            viewModel.login(email, password)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoggedIn.collect { loggedIn ->
                    if (loggedIn) {
                        startActivity(Intent(this@LoginActivity, MovieListActivity::class.java))
                        finish()
                    }
                }
            }
        }
    }
}
