package com.karimali.movieverse.features.splash.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karimali.movieverse.features.onboarding.domain.useCases.IsOnboardingCompletedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    isOnboardingCompletedUseCase: IsOnboardingCompletedUseCase,
) : ViewModel(){
    val isOnboardingCompleted : StateFlow<Boolean?> = isOnboardingCompletedUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )
}