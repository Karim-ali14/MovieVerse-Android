package com.karimali.movieverse.core.common.extensions

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}


fun View.applySystemBarInsets(top: Boolean = false, bottom: Boolean = false) {
    val initialTop = paddingTop
    val initialBottom = paddingBottom

    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.updatePadding(
            top = if (top) initialTop + bars.top else initialTop,
            bottom = if (bottom) initialBottom + bars.bottom else initialBottom,
        )
        insets
    }
    ViewCompat.requestApplyInsets(this)
}