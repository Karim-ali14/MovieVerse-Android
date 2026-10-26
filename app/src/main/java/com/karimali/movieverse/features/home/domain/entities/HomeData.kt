package com.karimali.movieverse.features.home.domain.entities

data class HomeData (
    val nowPlayingMovie : List<MovieEntity>,
    val topRatedMovie : List<MovieEntity>,
    val nowUpcomingMovie : List<MovieEntity>,
    val nowPopularMovie : List<MovieEntity>,
)