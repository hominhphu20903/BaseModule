package com.phuhm.basemodule.screen

import android.os.Bundle
import android.view.LayoutInflater
import com.phuhm.basemodule.R
import com.phuhm.basemodule.base.BaseActivity
import com.phuhm.basemodule.databinding.ActivityPermissionBinding
import com.phuhm.basemodule.extensions.changeNavigationBarColor
import com.phuhm.basemodule.extensions.changeStatusBarColor
import com.phuhm.basemodule.extensions.isPostNotificationPermissionGranted
import com.phuhm.basemodule.extensions.requestPostNotificationPermission
import com.phuhm.basemodule.extensions.setOnSingleClickListener
import com.phuhm.basemodule.extensions.showMessage
import com.phuhm.basemodule.shared.Constants

class PermissionActivity : BaseActivity<ActivityPermissionBinding>() {
    override fun inflateBinding(inflater: LayoutInflater): ActivityPermissionBinding {
        return ActivityPermissionBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        handleEvents()
        checkPermission()
    }

    private fun initViews() {
        initToolbar()
        changeStatusBarColor(R.color.primaryColor)
        changeNavigationBarColor(R.color.primaryColor)
    }

    private fun initToolbar() {
        binding.includeToolbar.tvTitle.text = getString(R.string.txt_permission)
    }

    private fun handleEvents() {
        binding.includeToolbar.btnBack.setOnSingleClickListener {
            finish()
        }

        binding.btnRequestPermission.setOnSingleClickListener {
            if (!isPostNotificationPermissionGranted()) {
                requestPostNotificationPermission()
            }
        }
    }

    private fun checkPermission() {
        if (isPostNotificationPermissionGranted()) {
            showMessage(getString(R.string.txt_permission_granted))
        } else {
            showMessage(getString(R.string.txt_permission_denied))
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.POST_NOTIFICATION_REQUEST_CODE) {
            checkPermission()
        }
    }
}