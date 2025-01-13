package com.phuhm.basemodule.screen

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import com.phuhm.basemodule.R
import com.phuhm.basemodule.base.BaseActivity
import com.phuhm.basemodule.databinding.ActivityConfigScreenBinding
import com.phuhm.basemodule.extensions.setNavigationBarTransparent
import com.phuhm.basemodule.extensions.setStatusBarTransparent

class ConfigScreenActivity : BaseActivity<ActivityConfigScreenBinding>() {
    override fun inflateBinding(inflater: LayoutInflater): ActivityConfigScreenBinding {
        return ActivityConfigScreenBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        setStatusBarTransparent()
        setNavigationBarTransparent()
        initVideoViewSplash()
    }

    private fun initVideoViewSplash() {
        val uri = Uri.parse("android.resource://${packageName}/${R.raw.video_splash}")
        binding.videoViewSplash.setVideoURI(uri)

        binding.videoViewSplash.setOnPreparedListener {
            binding.videoViewSplash.start()
        }

        binding.videoViewSplash.setOnCompletionListener {
            binding.videoViewSplash.start()
        }
    }
}