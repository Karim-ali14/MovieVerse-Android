package com.karimali.movieverse.core.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

open class BaseFragment<T: ViewBinding>(
    val strategy: BindingStrategy<T>
) : Fragment(
    when (strategy){
        is BindingStrategy.Bind -> strategy.layoutId
        is BindingStrategy.Inflate -> 0
    }
) {
    private var _binding :T? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return when (strategy) {
            is BindingStrategy.Inflate -> {
                _binding = strategy.inflate(inflater, container, false)
                 binding.root
            }
            is BindingStrategy.Bind ->{
                super.onCreateView(inflater, container, savedInstanceState)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (strategy is BindingStrategy.Bind)
            _binding = strategy.bind(view)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}