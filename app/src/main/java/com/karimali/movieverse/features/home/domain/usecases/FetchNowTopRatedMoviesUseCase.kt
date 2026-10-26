package com.karimali.movieverse.features.home.domain.usecases

import com.karimali.movieverse.features.home.domain.entities.MoviesResponseEntity
import com.karimali.movieverse.features.home.domain.repository.HomeRepository
import java.io.IOException
import javax.inject.Inject

class FetchNowTopRatedMoviesUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(): MoviesResponseEntity = throw IOException()


}