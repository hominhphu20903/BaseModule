package com.phuhm.basemodule.screen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.phuhm.basemodule.R
import com.phuhm.basemodule.base.BaseActivity
import com.phuhm.basemodule.databinding.ActivityMainBinding
import com.phuhm.basemodule.extensions.setNavigationBarColor
import com.phuhm.basemodule.extensions.setStatusBarColor
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
        setStatusBarColor(R.color.primaryColor)
        setNavigationBarColor(R.color.primaryColor)
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

        binding.btnRoom.setOnSingleClickListener {
            startRoomActivity()
        }

        binding.btnService.setOnSingleClickListener {
            startServiceActivity()
        }

        binding.btnConfigScreen.setOnSingleClickListener {
            startConfigScreenActivity()
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

    private fun startRoomActivity() {
        val intent = Intent(this, RoomActivity::class.java)
        startActivity(intent)
    }

    private fun startServiceActivity() {
        val intent = Intent(this, ServiceActivity::class.java)
        startActivity(intent)
    }

    private fun startConfigScreenActivity() {
        val intent = Intent(this, ConfigScreenActivity::class.java)
        startActivity(intent)
    }
}
