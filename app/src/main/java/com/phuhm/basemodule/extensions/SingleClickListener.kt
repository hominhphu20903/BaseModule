package com.phuhm.basemodule.extensions

import android.os.SystemClock
import android.view.View

class OnSingleClickListener(
    private val timeDelay: Long = 500L,
    private val action: () -> Unit
) : View.OnClickListener {

    companion object {
        private var lastClickTime: Long = 0L
    }

    override fun onClick(view: View) {
        if (SystemClock.elapsedRealtime() - lastClickTime < timeDelay) {
            return
        }
        lastClickTime = SystemClock.elapsedRealtime()
        action()
    }
}

fun View.setOnSingleClickListener(timeDelay: Long = 500L, action: () -> Unit) {
    setOnClickListener(OnSingleClickListener(timeDelay, action))
}
