package com.phuhm.basemodule.dialog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import com.phuhm.basemodule.base.BaseDialog
import com.phuhm.basemodule.databinding.DialogNoInternetBinding
import com.phuhm.basemodule.extensions.setOnSingleClickListener
import com.phuhm.basemodule.extensions.setupDialog

class NoInternetDialog(context: Context) : BaseDialog<DialogNoInternetBinding>(context) {
    override fun inflateBinding(inflater: LayoutInflater): DialogNoInternetBinding {
        return DialogNoInternetBinding.inflate(layoutInflater)
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
        binding.btnWifiSettings.setOnSingleClickListener {
            gotoSystemWifiSettings()
        }
    }

    private fun gotoSystemWifiSettings() {
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
        context.startActivity(intent)
    }
}