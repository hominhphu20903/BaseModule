package com.phuhm.basemodule.extensions

import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowCompat

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.setTopMarginForWindowInsets() {
    setOnApplyWindowInsetsListener { view, insets ->
        val statusBarSize = insets.systemWindowInsetTop
        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.topMargin = statusBarSize
        view.layoutParams = layoutParams

        insets
    }
}

fun View.setBottomMarginForWindowInsets() {
    setOnApplyWindowInsetsListener { view, insets ->
        val navigationBarSize = insets.systemWindowInsetBottom
        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.bottomMargin = navigationBarSize
        view.layoutParams = layoutParams

        insets
    }
}

fun View.setMarginsForWindowInsets() {
    setOnApplyWindowInsetsListener { view, insets ->
        val statusBarSize = insets.systemWindowInsetTop
        val navigationBarSize = insets.systemWindowInsetBottom
        val layoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.topMargin = statusBarSize
        layoutParams.bottomMargin = navigationBarSize
        view.layoutParams = layoutParams

        insets
    }
}

class OnSingleClickListener(private val action: () -> Unit) : View.OnClickListener {

    companion object{
        private var lastClickTime: Long = 0L
    }

    override fun onClick(view: View) {
        if (SystemClock.elapsedRealtime() - lastClickTime < 500) {
            return
        }

        lastClickTime = SystemClock.elapsedRealtime()

        action()
    }
}

fun View.setOnSingleClickListener(action: () -> Unit) {
    setOnClickListener(OnSingleClickListener(action))
}
