package com.karimali.movieverse.features.onboarding.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karimali.movieverse.features.onboarding.domain.useCases.SaveOnboardingStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val saveOnboardingStateUseCase: SaveOnboardingStateUseCase
) : ViewModel() {

    private val _onboardingCompleted : MutableSharedFlow<Unit> = MutableSharedFlow()
    val onboardingCompleted = _onboardingCompleted.asSharedFlow()

    fun saveOnboardingState(state: Boolean){
        viewModelScope.launch {
            saveOnboardingStateUseCase(completed = state)
            _onboardingCompleted.emit(Unit)
        }
    }
}