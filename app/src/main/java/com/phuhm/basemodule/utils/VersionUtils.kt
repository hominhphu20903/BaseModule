package com.phuhm.basemodule.utils

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

object VersionUtils {

    /**
     * @return true if device is running API >= 21 (Lollipop)
     */
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.LOLLIPOP)
    fun isLollipopPlus(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
    }

    /**
     * @return true if device is running API >= 23 (Marshmallow)
     */
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.M)
    fun isMarshmallowPlus(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }

    /**
     * @return true if device is running API >= 24 (Nougat)
     */
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.N)
    fun isNougatPlus(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
    }

    /**
     * @return true if device is running API >= 25 (Nougat MR1)
     */
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.N_MR1)
    fun isNougatMR1Plus(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1
    }

    /**
     * @return true if device is running API >= 26 (Oreo)
     */
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O)
    fun isOreoPlus(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
    }

    /**
     * @return true if device is running API >= 27 (Oreo MR1)
     */
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O_MR1)
    fun isOreoMR1Plus(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1
    }

    /**
     * @return true if device is running API >= 28 (Pie)
     */
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.P)
    fun isPiePlus(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
    }

    /**
     * @return true if device is running API >= 29 (Android 10 - Q)
     */
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q)
    fun isQPlus(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
    }

    /**
     * @return true if device is running API >= 30 (Android 11 - R)
     */
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
    fun isRPlus(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
    }

    /**
     * @return true if device is running API >= 31 (Android 12 - S)
     */
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
    fun isSPlus(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    }

    /**
     * @return true if device is running API >= 32 (Android 12L - S_V2)
     */
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S_V2)
    fun isSV2Plus(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.S_V2
    }

    /**
     * @return true if device is running API >= 33 (Android 13 - Tiramisu)
     */
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.TIRAMISU)
    fun isTiramisuPlus(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
    }

    /**
     * @return true if device is running API >= 34 (Android 14 - Upside Down Cake)
     */
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    fun isUpsideDownCakePlus(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE
    }

    /**
     * @return true if device is running API >= 35 (Android 15 - Vanilla Ice Cream)
     */
    @ChecksSdkIntAtLeast(api = 35)
    fun isVanillaIceCreamPlus(): Boolean {
        return Build.VERSION.SDK_INT >= 35
    }
}
