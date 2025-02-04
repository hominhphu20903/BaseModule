package com.phuhm.basemodule.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import com.phuhm.basemodule.R
import com.phuhm.basemodule.base.BaseActivity
import com.phuhm.basemodule.data.database.SharedPrefs
import com.phuhm.basemodule.databinding.ActivitySplashBinding
import com.phuhm.basemodule.extensions.setNavigationBarColor
import com.phuhm.basemodule.extensions.setStatusBarColor

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    companion object {
        private const val TIME_DELAY = 3000L
    }

    override fun inflateBinding(inflater: LayoutInflater): ActivitySplashBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()

        if(SharedPrefs.getInstance(this).isOpenedSplash) {
            startMainActivity()
        } else {
            Handler(mainLooper).postDelayed({
                startMainActivity()
            }, TIME_DELAY)
        }
    }

    private fun startMainActivity() {
        SharedPrefs.getInstance(this).isOpenedSplash = true

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun initViews() {
        setStatusBarColor(R.color.primaryColor)
        setNavigationBarColor(R.color.primaryColor)
    }
}