package com.karimali.movieverse.features.home.domain.repository

import com.karimali.movieverse.features.home.domain.entities.MoviesResponseEntity
import retrofit2.Response

interface HomeRepository {
    suspend fun fetchNowPlayingMovies(): MoviesResponseEntity

    suspend fun fetchNowUpcomingMovies(): MoviesResponseEntity

    suspend fun fetchNowPopularMovies(): MoviesResponseEntity

    suspend fun fetchNowTopRatedMovies(): MoviesResponseEntity
}