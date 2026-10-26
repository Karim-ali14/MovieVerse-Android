package com.karimali.movieverse.features.home.domain.usecases

import com.karimali.movieverse.features.home.domain.entities.MoviesResponseEntity
import com.karimali.movieverse.features.home.domain.repository.HomeRepository
import javax.inject.Inject

class FetchNowPopularMoviesUseCase @Inject constructor(
    private val homeRepository: HomeRepository
){
    suspend operator fun invoke() : MoviesResponseEntity = homeRepository.fetchNowPopularMovies()

}