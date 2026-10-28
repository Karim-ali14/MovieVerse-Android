package com.karimali.movieverse.features.onboarding.domain.useCases

import com.karimali.movieverse.features.onboarding.domain.repositories.OnboardingRepository
import javax.inject.Inject

class SaveOnboardingStateUseCase @Inject constructor(
    private val repository: OnboardingRepository
) {
    suspend operator fun invoke(completed : Boolean) {
        repository.saveOnboardingState(completed)
    }
}