package com.ajrr.mytoptenmovies

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ajrr.mytoptenmovies.data.datastore.UserPreferences
import com.ajrr.mytoptenmovies.presentation.login.LoginActivity
import com.ajrr.mytoptenmovies.presentation.login.movies.MovieListActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        userPreferences = UserPreferences(applicationContext)

        lifecycleScope.launch {
            delay(2500) // tiempo para el splash

            val isLoggedIn = userPreferences.isLoggedIn.first()

            if (isLoggedIn) {
                startActivity(Intent(this@SplashActivity, MovieListActivity::class.java))
            } else {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            }
            finish()
        }
    }
}
