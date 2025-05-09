package com.example.proyectodemoviles.data

import android.content.Context

class TokenManager(context: Context) {
    private val prefs = context.getSharedPreferences("appPrefs", Context.MODE_PRIVATE)

    fun saveAccessToken(token: String) {
        prefs.edit().putString("access_token", token).apply()
    }

    fun getAccessToken(): String? {
        return prefs.getString("access_token", null)
    }

    fun clearTokens() {
        prefs.edit().clear().apply()
    }
}