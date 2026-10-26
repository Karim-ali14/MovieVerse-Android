package com.karimali.movieverse.features.home.data.dataSource.remoteDataSource

import com.karimali.movieverse.core.network.AppRouter
import com.karimali.movieverse.features.home.data.models.MovieResponseDto
import retrofit2.http.GET

interface HomeApiService {
    @GET(AppRouter.NOW_PLAYING)
    suspend fun fetchNowPlayingMovies(): MovieResponseDto

    @GET(AppRouter.NOW_UPCOMING)
    suspend fun fetchNowUpcomingMovies(): MovieResponseDto

    @GET(AppRouter.NOW_POPULAR)
    suspend fun fetchNowPopularMovies(): MovieResponseDto

    @GET(AppRouter.NOW_TOP_RATED)
    suspend fun fetchNowTopRatedMovies(): MovieResponseDto
}