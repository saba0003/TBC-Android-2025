package com.example.tbc_android_2025.data.auth

object SessionManager {

    var authToken: String? = null

    fun clear() {
        authToken = null
    }
}
