package com.phuhm.basemodule.screen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.phuhm.basemodule.R
import com.phuhm.basemodule.base.BaseActivity
import com.phuhm.basemodule.databinding.ActivityServiceBinding
import com.phuhm.basemodule.extensions.isLocationPermissionGranted
import com.phuhm.basemodule.extensions.isPostNotificationPermissionGranted
import com.phuhm.basemodule.extensions.requestLocationPermission
import com.phuhm.basemodule.extensions.requestPostNotificationPermission
import com.phuhm.basemodule.extensions.setNavigationBarColor
import com.phuhm.basemodule.extensions.setOnSingleClickListener
import com.phuhm.basemodule.extensions.setStatusBarColor
import com.phuhm.basemodule.service.NotificationService
import com.phuhm.basemodule.shared.Constants

class ServiceActivity : BaseActivity<ActivityServiceBinding>() {
    override fun inflateBinding(inflater: LayoutInflater): ActivityServiceBinding {
        return ActivityServiceBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        handleEvents()
        checkPermission()
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

        binding.btnStartService.setOnSingleClickListener {
            startService()
        }

        binding.btnStopService.setOnSingleClickListener {
            stopService()
        }
    }

    private fun checkPermission() {
        if(isPostNotificationPermissionGranted() && !isLocationPermissionGranted()) {
            requestLocationPermission()
        } else {
            requestPostNotificationPermission()
        }
    }

    private fun startService() {
        val intent = Intent(this, NotificationService::class.java)
        startService(intent)
    }

    private fun stopService() {
        val intent = Intent(this, NotificationService::class.java)
        stopService(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == Constants.POST_NOTIFICATION_REQUEST_CODE) {
            if(isPostNotificationPermissionGranted()) {
               if(isLocationPermissionGranted()) {
                   startService()
               } else {
                   requestLocationPermission()
               }
            }
        }

        if(requestCode == Constants.LOCATION_PERMISSION_REQUEST_CODE) {
            if(isLocationPermissionGranted()) {
                startService()
            }
        }
    }
}