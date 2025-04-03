package com.example.btth04_01.helper

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

    fun saveUserData(username: String, password: String) {
        sharedPreferences.edit().apply {
            putString("USERNAME", username)
            putString("PASSWORD", password)
            apply() // Hoặc dùng commit() nếu cần đồng bộ ngay
        }
    }

    fun getUserData(): Pair<String?, String?> {
        val username = sharedPreferences.getString("USERNAME", "")
        val password = sharedPreferences.getString("PASSWORD", "")
        return Pair(username, password)
    }

    fun clearUserData() {
        sharedPreferences.edit().clear().apply()
    }
}