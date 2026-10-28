package com.karimali.movieverse.features.onboarding.domain.repositories

import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {
    fun isOnboardingCompleted() : Flow<Boolean>
    suspend fun saveOnboardingState(completed: Boolean)
}