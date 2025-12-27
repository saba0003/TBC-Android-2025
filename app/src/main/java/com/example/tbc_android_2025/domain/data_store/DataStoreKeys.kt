package com.example.tbc_android_2025.domain.data_store

import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreKeys {
    private const val AUTH_TOKEN = "auth_token"
    val TOKEN_KEY = stringPreferencesKey(name = AUTH_TOKEN)
}
