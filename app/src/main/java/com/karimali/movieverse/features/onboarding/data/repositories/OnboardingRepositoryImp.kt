package com.karimali.movieverse.features.onboarding.data.repositories

import com.karimali.movieverse.features.onboarding.data.local.OnboardingDataSource
import com.karimali.movieverse.features.onboarding.domain.repositories.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OnboardingRepositoryImp @Inject constructor(
    private val onboardingDataSource: OnboardingDataSource
) : OnboardingRepository {
    override fun isOnboardingCompleted(): Flow<Boolean> {
        return onboardingDataSource.isOnboardingCompleted
    }

    override suspend fun saveOnboardingState(completed: Boolean) {
        onboardingDataSource.setOnboardingCompleted(completed)
    }
}