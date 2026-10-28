package com.karimali.movieverse.features.home.data.repository

import com.karimali.movieverse.core.utils.safeApiCall
import com.karimali.movieverse.features.home.data.dataSource.remoteDataSource.HomeApiService
import com.karimali.movieverse.features.home.data.models.toEntity
import com.karimali.movieverse.features.home.domain.entities.MoviesResponseEntity
import com.karimali.movieverse.features.home.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeApiService: HomeApiService
) : HomeRepository {
    override suspend fun fetchNowPlayingMovies(): MoviesResponseEntity {
        return safeApiCall { homeApiService.fetchNowPlayingMovies().toEntity() }
    }

    override suspend fun fetchNowUpcomingMovies(): MoviesResponseEntity {
        return safeApiCall { homeApiService.fetchNowUpcomingMovies().toEntity() }
    }

    override suspend fun fetchNowPopularMovies(): MoviesResponseEntity {
        return safeApiCall { homeApiService.fetchNowPopularMovies().toEntity() }
    }

    override suspend fun fetchNowTopRatedMovies(): MoviesResponseEntity {
        return safeApiCall { homeApiService.fetchNowTopRatedMovies().toEntity() }
    }
}