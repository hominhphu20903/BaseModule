package com.phuhm.basemodule.screen

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import com.phuhm.basemodule.R
import com.phuhm.basemodule.base.BaseActivity
import com.phuhm.basemodule.databinding.ActivityNetworkChangeBinding
import com.phuhm.basemodule.dialog.NoInternetDialog
import com.phuhm.basemodule.extensions.changeNavigationBarColor
import com.phuhm.basemodule.extensions.changeStatusBarColor
import com.phuhm.basemodule.extensions.setOnSingleClickListener
import com.phuhm.basemodule.receiver.NetworkChangeReceiver

class NetworkChangeActivity : BaseActivity<ActivityNetworkChangeBinding>(), NetworkChangeReceiver.NetworkStateListener {
    private var noInternetDialog: NoInternetDialog? = null
    private var networkChangeReceiver: NetworkChangeReceiver? = null

    override fun inflateBinding(inflater: LayoutInflater): ActivityNetworkChangeBinding {
        return ActivityNetworkChangeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerNetworkChangeReceiver()
        initViews()
        handleEvents()
    }

    private fun registerNetworkChangeReceiver() {
        noInternetDialog = NoInternetDialog(this)
        networkChangeReceiver = NetworkChangeReceiver(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            @Suppress("DEPRECATION")
            registerReceiver(networkChangeReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION), RECEIVER_EXPORTED)
        } else {
            @Suppress("DEPRECATION")
            registerReceiver(networkChangeReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
    }

    private fun initViews() {
        initToolbar()
        changeStatusBarColor(R.color.primaryColor)
        changeNavigationBarColor(R.color.primaryColor)
    }

    private fun initToolbar() {
        binding.includeToolbar.tvTitle.text = getString(R.string.txt_network_change)
    }

    private fun handleEvents() {
        binding.includeToolbar.btnBack.setOnSingleClickListener {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterNetworkChangeReceiver()
    }

    private fun unregisterNetworkChangeReceiver() {
        if (networkChangeReceiver != null) {
            unregisterReceiver(networkChangeReceiver)
        }
    }

    override fun onConnected() {
        noInternetDialog?.dismiss()
    }

    override fun onDisconnected() {
        noInternetDialog?.show()
    }
}