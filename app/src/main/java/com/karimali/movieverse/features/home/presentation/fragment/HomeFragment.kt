package com.karimali.movieverse.features.home.presentation.fragment

import android.os.Bundle
import android.view.View
import com.karimali.movieverse.R
import com.karimali.movieverse.core.presentation.base.BaseFragment
import com.karimali.movieverse.core.presentation.base.BindingStrategy
import com.karimali.movieverse.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding>(
    strategy = BindingStrategy.Bind(
        bind = FragmentHomeBinding::bind,
        layoutId = R.layout.fragment_home
    ),
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}