package com.phuhm.basemodule.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.phuhm.basemodule.R
import com.phuhm.basemodule.base.BaseDialogFragment
import com.phuhm.basemodule.databinding.DialogConfirmBinding
import com.phuhm.basemodule.extensions.setOnSingleClickListener
import com.phuhm.basemodule.extensions.setupDialog
import com.phuhm.basemodule.extensions.showMessage

class ConfirmDialogFragment : BaseDialogFragment<DialogConfirmBinding>() {

    companion object {
        fun newInstance(): ConfirmDialogFragment {
            return ConfirmDialogFragment()
        }
    }

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): DialogConfirmBinding {
        return DialogConfirmBinding.inflate(layoutInflater)
    }

    override fun onStart() {
        super.onStart()
        dialog?.setupDialog(isFullScreen = false, isCancelable = false, isCancelOnTouchOutside = false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleEvents()
    }

    private fun handleEvents() {
        binding.btnAction.setOnSingleClickListener {
            requireContext().showMessage(requireContext().getString(R.string.txt_progress_action))
            dismiss()
        }
    }
}