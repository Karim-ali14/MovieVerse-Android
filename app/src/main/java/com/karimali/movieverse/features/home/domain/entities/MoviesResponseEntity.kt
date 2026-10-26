package com.karimali.movieverse.features.home.domain.entities

data class MoviesResponseEntity(
    val page: Int,
    val movies: List<MovieEntity>,
    val totalPages: Int,
    val totalResults: Int,
    val dates: DatesEntity?
)
