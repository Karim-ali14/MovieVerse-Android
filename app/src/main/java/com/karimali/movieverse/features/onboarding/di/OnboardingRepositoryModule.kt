package com.karimali.movieverse.features.onboarding.di

import com.karimali.movieverse.features.onboarding.data.repositories.OnboardingRepositoryImp
import com.karimali.movieverse.features.onboarding.domain.repositories.OnboardingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class OnboardingRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindOnboardingRepository(
        onboardingRepositoryImp: OnboardingRepositoryImp
    ): OnboardingRepository
}