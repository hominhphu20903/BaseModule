package com.phuhm.basemodule.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

abstract class BaseDialog<VB : ViewBinding>(context: Context) : Dialog(context) {
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.requestFeature(android.view.Window.FEATURE_NO_TITLE)
        _binding = inflateBinding(layoutInflater)
        setContentView(binding.root)
    }

    abstract fun inflateBinding(inflater: LayoutInflater): VB

    override fun show() {
        if(isShowing) return
        super.show()
    }

    override fun dismiss() {
        if (isShowing){
            super.dismiss()
        }
    }
}