package com.karimali.movieverse.features.home.data.models

import com.karimali.movieverse.features.home.domain.entities.DatesEntity

data class DatesDto(
    val maximum: String?,
    val minimum: String?
)

fun DatesDto.toEntity(): DatesEntity =
    DatesEntity(
        maxDate = maximum.orEmpty(),
        minDate = minimum.orEmpty()
    )
