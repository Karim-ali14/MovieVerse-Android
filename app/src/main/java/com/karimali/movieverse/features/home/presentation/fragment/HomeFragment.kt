package com.karimali.movieverse.features.home.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.karimali.movieverse.R
import com.karimali.movieverse.core.presentation.base.BaseFragment
import com.karimali.movieverse.core.presentation.base.BindingStrategy
import com.karimali.movieverse.databinding.FragmentHomeBinding
import com.karimali.movieverse.features.home.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    strategy = BindingStrategy.Bind(
        bind = FragmentHomeBinding::bind,
        layoutId = R.layout.fragment_home
    ),
) {

    private val viewmodel : HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED){
                viewmodel.homeDataState.collect {

                }
            }
        }
    }

}