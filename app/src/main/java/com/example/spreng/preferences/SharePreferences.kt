package com.example.spreng.preferences

import android.content.Context
import android.content.SharedPreferences

object UserManager {

    private const val PREF_NAME = "user_preferences"
    private const val KEY_USER_ID = "user_id"


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

}
