package com.phuhm.basemodule.screen

import android.os.Bundle
import android.view.LayoutInflater
import com.phuhm.basemodule.R
import com.phuhm.basemodule.base.BaseActivity
import com.phuhm.basemodule.databinding.ActivityPopupOptionsBinding
import com.phuhm.basemodule.extensions.setNavigationBarColor
import com.phuhm.basemodule.extensions.setOnSingleClickListener
import com.phuhm.basemodule.extensions.setStatusBarColor
import com.phuhm.basemodule.extensions.showPopupOptions

class PopupOptionsActivity : BaseActivity<ActivityPopupOptionsBinding>() {
    override fun inflateBinding(inflater: LayoutInflater): ActivityPopupOptionsBinding {
        return ActivityPopupOptionsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        handleEvents()
    }


    private fun initViews() {
        initToolbar()
        setStatusBarColor(R.color.primaryColor)
        setNavigationBarColor(R.color.primaryColor)
    }

    private fun initToolbar() {
        binding.includeToolbar.tvTitle.text = getString(R.string.txt_permission)
    }

    private fun handleEvents() {
        binding.includeToolbar.btnBack.setOnSingleClickListener {
            finish()
        }

        binding.btnShowPopupOptions.setOnSingleClickListener {
            val xOff = 0
            val yOff = -(binding.btnShowPopupOptions.height / 2)
            binding.btnShowPopupOptions.showPopupOptions(xOff, yOff)
        }
    }
}