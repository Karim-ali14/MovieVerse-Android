package com.karimali.movieverse.features.home.data.models

import com.karimali.movieverse.features.home.domain.entities.MoviesResponseEntity

data class MovieResponseDto(
    val dates: DatesDto?,
    val page: Int?,
    val results: List<MovieDto>?,
    val total_pages: Int?,
    val total_results: Int?
)

fun MovieResponseDto.toEntity(): MoviesResponseEntity =
    MoviesResponseEntity(
        page = page ?: 1,
        totalPages = total_pages ?: 1,
        totalResults = total_results ?: 0,
        dates = dates?.toEntity(),
        movies = results?.map { it.toEntity() }.orEmpty()
    )
