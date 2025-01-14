package com.phuhm.basemodule.receiver

import com.phuhm.basemodule.utils.NetworkUtils
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class NetworkChangeReceiver : BroadcastReceiver {

    private var networkStateListener: NetworkStateListener? = null

    constructor()

    constructor(listener: NetworkStateListener?) {
        networkStateListener = listener
    }

    override fun onReceive(context: Context, intent: Intent) {
        try {
            val action = intent.action
            if (action == ConnectivityManager.CONNECTIVITY_ACTION) {
                if (NetworkUtils.isNetworkConnected(context)) {
                    networkStateListener?.onNetworkConnected()
                } else {
                    networkStateListener?.onNetworkDisconnected()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    interface NetworkStateListener {
        fun onNetworkConnected()
        fun onNetworkDisconnected()
    }
}
