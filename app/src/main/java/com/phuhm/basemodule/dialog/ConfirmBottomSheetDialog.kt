package com.phuhm.basemodule.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.phuhm.basemodule.R
import com.phuhm.basemodule.base.BaseBottomSheetDialog
import com.phuhm.basemodule.databinding.BottomSheetDialogConfirmBinding
import com.phuhm.basemodule.extensions.setOnSingleClickListener
import com.phuhm.basemodule.extensions.setupBottomSheet
import com.phuhm.basemodule.extensions.showMessage

class ConfirmBottomSheetDialog : BaseBottomSheetDialog<BottomSheetDialogConfirmBinding>() {
    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): BottomSheetDialogConfirmBinding {
        return BottomSheetDialogConfirmBinding.inflate(layoutInflater)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        dialog.setupBottomSheet(
            isFullScreen = true,
            isCancelOnTouchOutside = true,
            isCancelable = true,
            isDraggable = true
        )
        return dialog
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