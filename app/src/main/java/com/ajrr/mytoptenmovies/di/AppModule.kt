package com.ajrr.mytoptenmovies.di

import com.ajrr.mytoptenmovies.data.datastore.remote.TmdbApiService
import com.ajrr.mytoptenmovies.data.datastore.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Named("tmdb_api_key")
    fun provideApiKey(): String = "9500bde5dd530fa44ec42b22bb1b08fa"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideTmdbApiService(retrofit: Retrofit): TmdbApiService {
        return retrofit.create(TmdbApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        @Named("tmdb_api_key") apiKey: String,
        apiService: TmdbApiService
    ): MovieRepository {
        return MovieRepository(apiKey, apiService)
    }
}
