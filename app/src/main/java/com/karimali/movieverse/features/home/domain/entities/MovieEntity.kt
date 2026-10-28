package com.karimali.movieverse.features.home.domain.entities

data class MovieEntity(
    val id: Int? = 0,
    val title: String? = "",
    val overview: String? = "",
    val posterUrl: String? = "",
    val backdropUrl: String? = "",
    val releaseDate: String? = "",
    val rating: Double? = 0.0,
    val voteCount: Int? = 0,
    val genres: List<Int>? = listOf(),
    val popularity: Double? = 0.0,
    val isAdult: Boolean? = false
)