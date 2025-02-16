package com.phuhm.basemodule.extensions

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

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

fun View.hideKeyboard() {
    val inputMethodManager = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.showKeyboard() {
    this.requestFocus()
    val inputMethodManager = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}