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
    return if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
    } else {
        true
    }
}

fun AppCompatActivity.requestPostNotificationPermission() {
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS),  Constants.POST_NOTIFICATION_REQUEST_CODE)
    }
}

fun Context.isLocationPermissionGranted() : Boolean {
    val hasFineLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    val hasCoarseLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED

    return hasFineLocation || hasCoarseLocation
}

fun AppCompatActivity.requestLocationPermission() {
    val permissionsToRequest = listOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    ActivityCompat.requestPermissions(this, permissionsToRequest.toTypedArray(), Constants.LOCATION_PERMISSION_REQUEST_CODE)
}

