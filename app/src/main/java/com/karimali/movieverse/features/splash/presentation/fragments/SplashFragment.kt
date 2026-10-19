package com.karimali.movieverse.features.splash.presentation.fragments

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.animation.doOnEnd
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.karimali.movieverse.core.presentation.base.BindingStrategy
import com.karimali.movieverse.core.presentation.base.BaseFragment
import com.karimali.movieverse.features.splash.presentation.viewmodel.SplashViewModel
import com.karimali.movieverse.R
import com.karimali.movieverse.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(
    strategy = BindingStrategy.Inflate(
        inflate = FragmentSplashBinding::inflate
    )
) {

    private val splashViewModel by viewModels<SplashViewModel>()
    private var animatorSet: AnimatorSet? = null


    private lateinit var animationFinished : CompletableDeferred<Unit>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animationFinished = CompletableDeferred()
        startLogoAnimation()
        observeOnboardingState()

    }

    private fun observeOnboardingState(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {
                val isCompleted = splashViewModel
                    .isOnboardingCompleted
                    .filterNotNull()
                    .first()

                animationFinished.await()

                navigateToNextDestination(isCompleted)
            }
        }
    }

    private fun navigateToNextDestination(isCompleted: Boolean) {
        val navController = findNavController()

        if (navController.currentDestination?.id != R.id.splashFragment) {
            return
        }

        val direction =
            if (isCompleted){
                SplashFragmentDirections.actionSplashFragmentToHomeFragment()
            } else {
                SplashFragmentDirections.actionSplashFragmentToOnBoardingFragment()
            }

        navController.navigate(direction)
    }

    private fun startLogoAnimation() {
        val logo = binding.ivLogo

        val alpha = ObjectAnimator.ofFloat(logo, View.ALPHA , 0f,1f).apply {
            duration = 2000
        }

        val scaleX = ObjectAnimator.ofFloat(logo, View.SCALE_X,0.5f , 1f).apply {
            duration = 800
        }

        val scaleY = ObjectAnimator.ofFloat(logo, View.SCALE_Y,0.5f , 1f).apply {
            duration = 800
        }

        animatorSet = AnimatorSet().apply {
            playTogether(alpha, scaleX, scaleY)
            doOnEnd { animationFinished.complete(Unit) }
            start()
        }
    }

    override fun onDestroyView() {
        animatorSet?.cancel()
        animatorSet = null
        super.onDestroyView()
    }
}