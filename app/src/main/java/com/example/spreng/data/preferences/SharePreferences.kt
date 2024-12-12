package com.example.spreng.data.preferences

import android.content.Context
import android.content.SharedPreferences

object UserManager {

    private const val PREF_NAME = "user_preferences"
    private const val KEY_USER_ID = "user_id"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    // Lưu userId vào SharedPreferences
    fun saveUserId(context: Context, userId: Long) {
        val prefs = getSharedPreferences(context)
        prefs.edit().putLong(KEY_USER_ID, userId).apply()
    }

    // Lấy userId từ SharedPreferences
    fun getUserId(context: Context): Long {
        val prefs = getSharedPreferences(context)
        return prefs.getLong(KEY_USER_ID, -1)
    }

    fun saveLoginState(context: Context, isLoggedIn: Boolean) {
        val prefs = getSharedPreferences(context)
        prefs.edit().putBoolean(KEY_IS_LOGGED_IN, isLoggedIn).apply()
    }

    fun isUserLoggedIn(context: Context): Boolean {
        val prefs = getSharedPreferences(context)
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false)
    }

}
