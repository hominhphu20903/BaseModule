package com.phuhm.basemodule.extensions

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.PopupWindow
import androidx.constraintlayout.widget.ConstraintLayout
import com.phuhm.basemodule.databinding.PopupOptionsBinding

fun View.showPopupOptions(xOff: Int = 0, yOff: Int = 0) {
    val bindingOptionMenu = PopupOptionsBinding.inflate(LayoutInflater.from(this.context))
    val popupView = bindingOptionMenu.root

    val popupWindow = PopupWindow(
        popupView, ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT
    )

    popupWindow.isOutsideTouchable = true
    popupWindow.isFocusable = true
    popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    popupWindow.showAsDropDown(this, xOff, yOff, Gravity.START)
}