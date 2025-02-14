package com.phuhm.basemodule.extensions

import android.os.SystemClock
import android.view.View
import android.view.ViewGroup

fun View.visible() {
    if (visibility == View.VISIBLE) return
    visibility = View.VISIBLE
}

fun View.gone() {
    if (visibility == View.GONE) return
    visibility = View.GONE
}

fun View.invisible() {
    if (visibility == View.INVISIBLE) return
    visibility = View.INVISIBLE
}

fun View.setBackgroundTint(color: Int) {
    this.setBackgroundTintList(context.getColorStateList(color))
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

fun View.setTopPaddingForWindowInsets() {
    setOnApplyWindowInsetsListener { view, insets ->
        val statusBarSize = insets.systemWindowInsetTop
        view.setPadding(
            view.paddingLeft,
            statusBarSize,
            view.paddingRight,
            view.paddingBottom
        )

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

fun View.setBottomPaddingForWindowInsets() {
    setOnApplyWindowInsetsListener { view, insets ->
        val statusBarSize = insets.systemWindowInsetBottom
        view.setPadding(
            view.paddingLeft,
            statusBarSize,
            view.paddingRight,
            view.paddingBottom
        )

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

fun View.setPaddingsForWindowInsets() {
    setOnApplyWindowInsetsListener { view, insets ->
        val statusBarSize = insets.systemWindowInsetTop
        val navigationBarSize = insets.systemWindowInsetBottom
        view.setPadding(
            view.paddingLeft,
            statusBarSize,
            view.paddingRight,
            navigationBarSize
        )

        insets
    }
}

class OnSingleClickListener(private val action: () -> Unit) : View.OnClickListener {

    companion object {
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