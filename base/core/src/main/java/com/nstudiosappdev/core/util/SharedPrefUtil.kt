package com.nstudiosappdev.core.util

import android.content.Context
import android.content.SharedPreferences

object SharedPrefUtil {

    private const val MASTER_KEY = "[Stocker!]"

    private fun getSharedPref(context: Context): SharedPreferences {
        return context.getSharedPreferences(MASTER_KEY, Context.MODE_PRIVATE)
    }

    /**
     * Put Methods
     */

    fun put(context: Context, key: String, value: String?) {
        getSharedPref(context).edit().putString(key, value).apply()
    }

    fun put(context: Context, key: String, value: Boolean) {
        getSharedPref(context).edit().putBoolean(key, value).apply()
    }

    fun put(context: Context, key: String, value: Long) {
        getSharedPref(context).edit().putLong(key, value).apply()
    }

    /**
     * Get Methods
     */

    fun get(context: Context, key: String, defaultVal: String?): String? {
        return getSharedPref(context).getString(key, defaultVal)
    }

    fun get(context: Context, key: String, defaultVal: Boolean): Boolean {
        return getSharedPref(context).getBoolean(key, defaultVal)
    }

    fun get(context: Context, key: String, defaultVal: Long): Long {
        return getSharedPref(context).getLong(key, defaultVal)
    }

    /**
     * Clear methods
     */

    fun clearData(context: Context) {
        getSharedPref(context).edit().clear().apply()
    }

    fun remove(context: Context, key: String) {
        getSharedPref(context).edit().remove(key).apply()
    }
}

