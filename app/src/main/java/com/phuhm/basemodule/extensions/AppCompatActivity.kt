package com.phuhm.basemodule.extensions

import android.graphics.Color
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat


fun AppCompatActivity.setStatusBarColor(color: Int) {
    val window = this.window
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = ContextCompat.getColor(this, color)
}

fun AppCompatActivity.setStatusBarTransparent() {
    this.window.statusBarColor = Color.TRANSPARENT
    WindowCompat.setDecorFitsSystemWindows(this.window, false)
}

fun AppCompatActivity.setAppearanceLightStatusBars(isLight: Boolean) {
    val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
    windowInsetsController.isAppearanceLightStatusBars = isLight
}

fun AppCompatActivity.setNavigationBarColor(color: Int) {
    val window = this.window
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.navigationBarColor = ContextCompat.getColor(this, color)
}

fun AppCompatActivity.setNavigationBarTransparent() {
    this.window.navigationBarColor = Color.TRANSPARENT
    WindowCompat.setDecorFitsSystemWindows(this.window, false)
}

fun AppCompatActivity.setAppearanceLightNavigationBars(isLight: Boolean) {
    val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
    windowInsetsController.isAppearanceLightNavigationBars = isLight
}

