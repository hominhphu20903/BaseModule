package com.phuhm.basemodule.screen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.phuhm.basemodule.R
import com.phuhm.basemodule.base.BaseActivity
import com.phuhm.basemodule.databinding.ActivityMainBinding
import com.phuhm.basemodule.extensions.changeNavigationBarColor
import com.phuhm.basemodule.extensions.changeStatusBarColor
import com.phuhm.basemodule.extensions.setOnSingleClickListener

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun inflateBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        handleEvents()
    }

    private fun initViews() {
        changeStatusBarColor(R.color.primaryColor)
        changeNavigationBarColor(R.color.primaryColor)
    }

    private fun handleEvents() {
        binding.btnPermission.setOnSingleClickListener {
            startPermissionActivity()
        }

        binding.btnDialog.setOnSingleClickListener {
            startDialogActivity()
        }

        binding.btnNetworkChange.setOnSingleClickListener {
            startNetworkChangeActivity()
        }

        binding.btnCallApi.setOnSingleClickListener {
            startCallApiActivity()
        }
    }

    private fun startPermissionActivity() {
        val intent = Intent(this, PermissionActivity::class.java)
        startActivity(intent)
    }

    private fun startDialogActivity() {
        val intent = Intent(this, DialogActivity::class.java)
        startActivity(intent)
    }

    private fun startNetworkChangeActivity() {
        val intent = Intent(this, NetworkChangeActivity::class.java)
        startActivity(intent)
    }

    private fun startCallApiActivity() {
        val intent = Intent(this, CallApiActivity::class.java)
        startActivity(intent)
    }
}
