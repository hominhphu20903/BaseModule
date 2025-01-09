package com.phuhm.basemodule.extensions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.phuhm.basemodule.shared.Constants

fun Context.isPostNotificationPermissionGranted() : Boolean {
    return if(Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
        true
    } else {
        ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
    }
}

fun AppCompatActivity.requestPostNotificationPermission() {
    if(Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
        return
    }
    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS),  Constants.POST_NOTIFICATION_REQUEST_CODE)
}