package com.phuhm.basemodule.base

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding

abstract class BaseBottomSheetDialog<VB : ViewBinding> : BottomSheetDialogFragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateBinding(inflater, container)
        return binding.root
    }

    abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (isAdded) return
        super.show(manager, tag)
    }

    override fun dismiss() {
        if (isAdded) {
            super.dismiss()
        }
    }
}