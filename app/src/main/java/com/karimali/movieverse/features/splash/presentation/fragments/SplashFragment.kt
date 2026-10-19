package com.karimali.movieverse.features.splash.presentation.fragments

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.karimali.collectallandroidfeatures.core.presentation.base.BindingStrategy
import com.karimali.collectallandroidfeatures.core.presentation.base.BaseFragment
import com.karimali.movieverse.features.splash.presentation.viewmodel.SplashViewModel
import com.karimali.movieverse.R
import com.karimali.movieverse.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.debounce
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        startLogoAnimation()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {
                val isCompleted = splashViewModel
                    .isOnboardingCompleted
                    .filterNotNull()
                    .first()

                val navController = findNavController()

                if (navController.currentDestination?.id != R.id.splashFragment) {
                    return@repeatOnLifecycle
                }

                val direction =
                    if (isCompleted) {
                        // Todo go to home
                    } else {
                        // Todo go to onboarding
                    }

//                navController.navigate(direction)
            }
        }

    }

    private fun startLogoAnimation() {
        val animation = AnimationUtils.loadAnimation(requireContext(),R.anim.splash_animation)
        binding.ivLogo.startAnimation(animation)
    }
}