package com.phuhm.basemodule.data.database

import android.content.Context
import com.phuhm.basemodule.shared.Constants

object SharedPrefs {
    private lateinit var sharePrefs: android.content.SharedPreferences

    fun init(context: Context) {
        sharePrefs = context.getSharedPreferences(Constants.DB_NAME, Context.MODE_PRIVATE)
    }

    var isOpenedSplash: Boolean
        get() = sharePrefs.getBoolean(Constants.IS_OPENED_SPLASH, false)
        set(value) {
            sharePrefs.edit().putBoolean(Constants.IS_OPENED_SPLASH, value).apply()
        }
}
