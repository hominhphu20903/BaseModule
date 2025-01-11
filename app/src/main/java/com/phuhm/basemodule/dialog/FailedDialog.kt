package com.phuhm.basemodule.dialog

import android.animation.Animator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import com.phuhm.basemodule.base.BaseDialog
import com.phuhm.basemodule.databinding.DialogFailedBinding
import com.phuhm.basemodule.extensions.setupDialog

class FailedDialog(context: Context) : BaseDialog<DialogFailedBinding>(context) {
    override fun inflateBinding(inflater: LayoutInflater): DialogFailedBinding {
        return DialogFailedBinding.inflate(layoutInflater)
    }

    override fun onStart() {
        super.onStart()
        setupDialog(isFullScreen = false, isCancelable = false, isCancelOnTouchOutside = false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleEvents()
    }

    private fun handleEvents() {
        binding.loadingAnim.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {}
            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {
                dismiss()
            }
        })
    }
}