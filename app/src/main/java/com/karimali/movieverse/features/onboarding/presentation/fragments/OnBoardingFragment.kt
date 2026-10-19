package com.karimali.movieverse.features.onboarding.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.karimali.movieverse.core.presentation.base.BindingStrategy
import com.karimali.movieverse.R
import com.karimali.movieverse.core.common.utils.Constants
import com.karimali.movieverse.databinding.FragmentOnBoardingBinding
import com.karimali.movieverse.features.onboarding.presentation.adapters.OnBoardingAdapter
import com.karimali.movieverse.core.presentation.base.BaseFragment
import com.karimali.movieverse.features.onboarding.presentation.viewModels.OnboardingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OnBoardingFragment : BaseFragment<FragmentOnBoardingBinding>(
    strategy = BindingStrategy.Bind(
        bind = FragmentOnBoardingBinding::bind,
        layoutId = R.layout.fragment_on_boarding
    ),
) {

    private val onboardingViewModel by viewModels<OnboardingViewModel>()
    private var pagerListener: ViewPager2.OnPageChangeCallback? = null
    private lateinit var onboardingAdapter: OnBoardingAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        setupPagerListener()
        setupClickListeners()
        observeCompleteOnboardingState()
    }

    private fun observeCompleteOnboardingState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                onboardingViewModel.onboardingCompleted.collect {
                    findNavController().navigate(
                        OnBoardingFragmentDirections.actionOnBoardingFragmentToHomeFragment()
                    )
                }
            }
        }
    }

    private fun setupClickListeners() {
       binding.run {
           nextBtn.setOnClickListener {
               val currentItem = viewPager.currentItem
               val lastIndex = Constants.onBoardingList.lastIndex

               if (currentItem < lastIndex) {
                   viewPager.setCurrentItem(currentItem + 1, true)
               } else {
                   onboardingViewModel.saveOnboardingState(true)
               }
           }
           backBtn.setOnClickListener {
               val currentItem = viewPager.currentItem
               if (currentItem > 0) {
                   viewPager.setCurrentItem(currentItem - 1, true)
               }
           }
       }
    }

    private fun setupPagerListener() {
        pagerListener = object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.isFirstPage = position != 0
                binding.isLastPage = position == Constants.onBoardingList.lastIndex
            }
        }

        pagerListener?.let { callback ->
            binding.viewPager.registerOnPageChangeCallback(callback)
        }
    }

    private fun setupViewPager() {
        onboardingAdapter = OnBoardingAdapter()
        binding.viewPager.adapter = onboardingAdapter

        onboardingAdapter.submitList(Constants.onBoardingList)
    }

    override fun onDestroyView() {
        pagerListener?.let { callback ->
            binding.viewPager.unregisterOnPageChangeCallback(callback)
        }
        pagerListener = null
        binding.viewPager.adapter = null
        super.onDestroyView()
    }
}