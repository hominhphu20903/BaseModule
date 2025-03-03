package com.phuhm.basemodule.utils

import android.app.Activity
import com.phuhm.basemodule.shared.Constants
import com.phuhm.basemodule.shared.MyApplication

object AppPrefs {
    private val sharedPrefs = MyApplication.context.getSharedPreferences(Constants.DB_NAME, Activity.MODE_PRIVATE)

    var isOpenedSplash
        get() = sharedPrefs.getBoolean(Constants.IS_OPENED_SPLASH, true)
        set(value) {
            sharedPrefs.edit().putBoolean(Constants.IS_OPENED_SPLASH, value).apply()
        }

    fun clearAll() {
        sharedPrefs.edit().clear().apply()
    }
}