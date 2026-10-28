package com.karimali.movieverse.features.more.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karimali.movieverse.R
import com.karimali.movieverse.core.presentation.base.BaseFragment
import com.karimali.movieverse.core.presentation.base.BindingStrategy
import com.karimali.movieverse.databinding.FragmentMoreBinding

class MoreFragment : BaseFragment<FragmentMoreBinding>(
    strategy = BindingStrategy.Bind(
        bind = FragmentMoreBinding::bind,
        layoutId = R.layout.fragment_more
    )
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}