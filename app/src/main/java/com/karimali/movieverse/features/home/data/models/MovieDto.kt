package com.karimali.movieverse.features.home.data.models

import com.karimali.movieverse.core.common.utils.Constants.IMAGE_BASE_URL
import com.karimali.movieverse.features.home.domain.entities.MovieEntity
import kotlin.text.orEmpty

data class MovieDto(
    val adult: Boolean?,
    val backdrop_path: String?,
    val genre_ids: List<Int>?,
    val id: Int?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
)

fun MovieDto.toEntity(): MovieEntity =
    MovieEntity(
        id = id ?: 0,
        title = title.orEmpty(),
        overview = overview.orEmpty(),
        posterUrl = poster_path?.let { "$IMAGE_BASE_URL$it" },
        backdropUrl = backdrop_path?.let { "$IMAGE_BASE_URL$it" },
        releaseDate = release_date.orEmpty(),
        rating = vote_average ?: 0.0,
        voteCount = vote_count ?: 0,
        popularity = popularity ?: 0.0,
        isAdult = adult ?: false,
        genres = genre_ids.orEmpty(),
    )
