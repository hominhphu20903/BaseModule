package com.phuhm.basemodule.extensions

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

fun Dialog.setupDialog(
    isFullScreen: Boolean,
    isCancelable: Boolean,
    isCancelOnTouchOutside: Boolean,
    amount: Float = 0.5f
) {
    this.apply {
        setCanceledOnTouchOutside(isCancelOnTouchOutside)
        setCancelable(isCancelable)

        window?.let { window ->
            if (isFullScreen) {
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            } else {
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            }

            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            window.setDimAmount(amount)
        }
    }
}

fun BottomSheetDialog.setupBottomSheet(
    isFullScreen: Boolean,
    isCancelable: Boolean,
    isCancelOnTouchOutside: Boolean,
    isDraggable: Boolean,
) {
    this.apply {
        setCanceledOnTouchOutside(isCancelOnTouchOutside)
        setCancelable(isCancelable)

        window?.let { window ->
            if (isFullScreen) {
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            } else {
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            }

            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        behavior.state = if (isFullScreen) BottomSheetBehavior.STATE_EXPANDED else BottomSheetBehavior.STATE_COLLAPSED
        behavior.isDraggable = isDraggable
    }
}