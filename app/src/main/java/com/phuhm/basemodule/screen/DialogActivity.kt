package com.phuhm.basemodule.screen

import android.os.Bundle
import android.view.LayoutInflater
import com.phuhm.basemodule.R
import com.phuhm.basemodule.base.BaseActivity
import com.phuhm.basemodule.databinding.ActivityDialogBinding
import com.phuhm.basemodule.dialog.ConfirmBottomSheetDialog
import com.phuhm.basemodule.dialog.ConfirmDialog
import com.phuhm.basemodule.dialog.ConfirmDialogFragment
import com.phuhm.basemodule.extensions.changeNavigationBarColor
import com.phuhm.basemodule.extensions.changeStatusBarColor
import com.phuhm.basemodule.extensions.setOnSingleClickListener

class DialogActivity : BaseActivity<ActivityDialogBinding>() {
    override fun inflateBinding(inflater: LayoutInflater): ActivityDialogBinding {
        return ActivityDialogBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        handleEvents()
    }

    private fun initViews() {
        initToolbar()
        changeStatusBarColor(R.color.primaryColor)
        changeNavigationBarColor(R.color.primaryColor)
    }

    private fun initToolbar() {
        binding.includeToolbar.tvTitle.text = getString(R.string.txt_dialog)
    }

    private fun handleEvents() {
        binding.includeToolbar.btnBack.setOnSingleClickListener {
            finish()
        }

        binding.btnShowDialog.setOnSingleClickListener {
            showConfirmDialog()
        }

        binding.btnShowDialogFragment.setOnSingleClickListener {
            showConfirmDialogFragment()
        }

        binding.btnShowBottomSheetDialog.setOnSingleClickListener {
            showConfirmBottomSheetDialog()
        }
    }

    private fun showConfirmDialog() {
        val dialog = ConfirmDialog(this)
        dialog.show()
    }

    private fun showConfirmDialogFragment() {
        val dialog = ConfirmDialogFragment.newInstance()
        dialog.show(supportFragmentManager, ConfirmDialogFragment::class.java.simpleName)
    }

    private fun showConfirmBottomSheetDialog() {
        val dialog = ConfirmBottomSheetDialog()
        dialog.show(supportFragmentManager, ConfirmBottomSheetDialog::class.java.simpleName)
    }
}