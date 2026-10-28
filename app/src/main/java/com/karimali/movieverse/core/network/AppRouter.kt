package com.karimali.movieverse.core.network

object AppRouter {
    const val BASE_URL = "https://api.themoviedb.org/3/"

    const val NOW_PLAYING = "movie/now_playing"
    const val NOW_UPCOMING = "movie/upcoming"
    const val NOW_TOP_RATED = "movie/top_rated"
    const val NOW_POPULAR = "movie/popular"

    const val MOVIE_DETAILS = "movie/{movie_id}"

    const val FAVORITE = "account/{account_id}/favorite"
}