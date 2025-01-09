package com.phuhm.basemodule.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import com.phuhm.basemodule.R
import com.phuhm.basemodule.base.BaseDialog
import com.phuhm.basemodule.databinding.DialogConfirmBinding
import com.phuhm.basemodule.extensions.setOnSingleClickListener
import com.phuhm.basemodule.extensions.setupDialog
import com.phuhm.basemodule.extensions.showMessage

class ConfirmDialog(context: Context) : BaseDialog<DialogConfirmBinding>(context) {
    override fun inflateBinding(inflater: LayoutInflater): DialogConfirmBinding {
        return DialogConfirmBinding.inflate(layoutInflater)
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
        binding.btnAction.setOnSingleClickListener {
            context.showMessage(context.getString(R.string.txt_progress_action))
            dismiss()
        }
    }
}