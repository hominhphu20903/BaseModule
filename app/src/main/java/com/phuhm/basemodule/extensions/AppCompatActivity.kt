package com.phuhm.basemodule.extensions

import android.graphics.Color
import android.os.Build
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

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

fun AppCompatActivity.setHideStatusBar() {
    WindowCompat.setDecorFitsSystemWindows(this.window, false)

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        this.window.attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
    }

    val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
    windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

    ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { view, windowInsets ->
        windowInsetsController.hide(WindowInsetsCompat.Type.statusBars())
        ViewCompat.onApplyWindowInsets(view, windowInsets)
    }
}

fun AppCompatActivity.setHideNavigationBar() {
    WindowCompat.setDecorFitsSystemWindows(this.window, false)

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        this.window.attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
    }

    val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
    windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

    ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { view, windowInsets ->
        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())
        ViewCompat.onApplyWindowInsets(view, windowInsets)
    }
}

fun AppCompatActivity.setFullScreen() {
    WindowCompat.setDecorFitsSystemWindows(this.window, false)

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        this.window.attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
    }

    val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
    windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

    ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { view, windowInsets ->
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        ViewCompat.onApplyWindowInsets(view, windowInsets)
    }
}

