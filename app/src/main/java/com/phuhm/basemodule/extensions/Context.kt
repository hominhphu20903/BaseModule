package com.phuhm.basemodule.extensions

import android.content.Context
import android.util.TypedValue
import android.widget.Toast

fun Context.dpToPx(dp: Int): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics).toInt()
}

fun Context.spToPx(dp: Int): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dp.toFloat(), resources.displayMetrics)
}

fun Context.showMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showMessage(message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

