package com.phuhm.basemodule.data.database

import android.annotation.SuppressLint
import android.content.Context
import com.phuhm.basemodule.shared.Constants

class SharedPrefs(context: Context) {
    private val sharePrefs = context.getSharedPreferences(Constants.DB_NAME, Context.MODE_PRIVATE)
    private val editor = sharePrefs.edit()

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var INSTANCE: SharedPrefs? = null
        fun getInstance(context: Context): SharedPrefs {
            if (INSTANCE == null) {
                INSTANCE = SharedPrefs(context)
            }
            return INSTANCE!!
        }
    }

    var isOpenedSplash: Boolean
        get() = sharePrefs.getBoolean(Constants.IS_OPENED_SPLASH, false)
        set(value) {
            editor.putBoolean(Constants.IS_OPENED_SPLASH, value).apply()
            sharePrefs.edit().putBoolean(Constants.IS_OPENED_SPLASH, value).apply()
        }

    fun clearAll() {
        editor.clear().apply()
    }
}
