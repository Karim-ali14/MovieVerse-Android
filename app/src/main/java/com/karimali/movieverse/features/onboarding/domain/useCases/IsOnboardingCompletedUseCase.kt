package com.karimali.movieverse.features.onboarding.domain.useCases

import com.karimali.movieverse.features.onboarding.domain.repositories.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsOnboardingCompletedUseCase @Inject constructor(
    private val onBoardingRepository: OnboardingRepository
){
    operator fun invoke() : Flow<Boolean> = onBoardingRepository.isOnboardingCompleted()

}