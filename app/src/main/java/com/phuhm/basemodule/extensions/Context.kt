package com.phuhm.basemodule.extensions

import android.content.Context
import android.util.TypedValue
import android.widget.Toast

fun Context.dpToPx(dp: Int): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        resources.displayMetrics
    )
}

fun Context.spToPx(sp: Int): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        sp.toFloat(),
        resources.displayMetrics
    )
}

fun Context.pxToDp(px: Int): Float {
    return px / resources.displayMetrics.density
}

fun Context.pxToSp(px: Int): Float {
    return px / resources.displayMetrics.scaledDensity
}

fun Context.showMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showMessage(message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

