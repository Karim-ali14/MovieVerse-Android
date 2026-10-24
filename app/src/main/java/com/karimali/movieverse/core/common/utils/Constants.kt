package com.karimali.movieverse.core.common.utils

import com.karimali.movieverse.R
import com.karimali.movieverse.features.onboarding.data.models.OnboardingModel


object Constants {

    const val AUTH_TOKEN =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYTNlMjRlOTRmYzQ4NjM0ZjdmNTdiZjgyZmQ5YmU1ZCIsIm5iZiI6MTY3NjkyNTg4My41OTcsInN1YiI6IjYzZjNkYmJiMTUzNzZjMDA4MzM3NGFhYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.EE6Pdxwk9HxFfUZOK3AvUur5dJtD_rZ4ssPAjzs7HCE"

    const val ACCOUNT_ID = 17809764

    val topLevelDestinations = setOf(
        R.id.homeFragment,
        R.id.searchFragment,
        R.id.favoriteFragment,
        R.id.moreFragment,
    )

//    val destinationsWithBottomNav = setOf(
//
//    )

    val destinationsWithoutToolBar = setOf(
        R.id.splashFragment,
        R.id.onBoardingFragment,
        R.id.homeFragment,
        R.id.moreFragment
        )

    val onBoardingList = arrayListOf(
        OnboardingModel(
            id = 0,
            imageId = R.drawable.onboarding_one
        ),
        OnboardingModel(
            id = 1,
            imageId = R.drawable.onboarding_two
        ),
        OnboardingModel(
            id = 2,
            imageId = R.drawable.onboarding_three
        ),
    )

}