package com.karimali.movieverse.core.presentation.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding


sealed class BindingStrategy<T : ViewBinding> {
    class Inflate<T: ViewBinding>(
        val inflate: (LayoutInflater, ViewGroup?, Boolean) -> T
    ) : BindingStrategy<T>()

    class Bind<T: ViewBinding>(
        val bind: (View) -> T,
        val layoutId:Int
    ) : BindingStrategy<T>()
}